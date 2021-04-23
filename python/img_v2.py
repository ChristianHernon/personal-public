"Image Optimizer"

# library imports
import os
import re
from time import time
from multiprocessing import cpu_count as get_cpu_count
from sys import stdout
from threading import Thread
from tkinter.filedialog import askdirectory as get_directory
from PIL import Image


class Worker(Thread):
    "Class for threaded worker process"

    def __init__(self, thread_id, images_array, working_dir, max_dim, stats, output_dir):
        "Constructor"
        Thread.__init__(self)
        self.thread_id = thread_id
        self.images_array = images_array
        self.stats = stats
        self.working_dir = working_dir
        self.max_dim = max_dim
        self.output_dir = output_dir

    def run(self):
        "Run when the thread starts"
        while len(self.images_array) > 0:
            try:
                optimize_image(self.images_array.pop(0), self.working_dir,
                               self.max_dim, self.output_dir, self.stats)
                self.stats['progress'] = progress_tracker(self.stats)
            except IndexError:
                pass
    # end class Worker


def init_script():
    "Begin the program"

    max_dim = 1920
    img_re = re.compile(r"^.+\.(gif|jpg|png)")
    working_dir = get_directory() + "/"
    img_array = [f for f in os.listdir(working_dir)
                 if re.search(img_re, f)]

    output_folder = create_output_folder(working_dir, max_dim)
    threads_array = []
    cpu_count = get_cpu_count()

    stats = {
        "sizeBefore": 0,
        "sizeAfter": 0,
        "completed": 0,
        "progress": 0,
        "total": len(img_array)
    }

    start_time = time()

    startProgress()

    for cpu in range(cpu_count):
        threads_array.append(
            Worker(cpu, img_array, working_dir, max_dim, stats, output_folder))

    for thread in threads_array:
        thread.start()

    for thread in threads_array:
        thread.join()

    endProgress(stats)

    elapsed_time = round(time() - start_time, 2)
    print(str(stats["total"]) + " Images processed in " +
          str(elapsed_time) + "s")

    print("Size reduced by " + str(
        100 - round(stats["sizeAfter"] * 100 / stats["sizeBefore"], 2)) + "%")

    # end init_script


def create_output_folder(working_dir, max_dim):
    "Create the output folder for the optimized images"

    new_path = working_dir + "/optimized_" + str(max_dim)

    if not os.path.isdir(new_path):
        os.mkdir(new_path)
        return new_path
    else:
        x = 0
        while os.path.isdir(new_path + "-" + str(x)):
            x += 1
        new_path += "-" + str(x)
        os.mkdir(new_path)
        return new_path


def progress_tracker(stats):
    "Roughly approximate the progress by tracking how many images are complete"

    x = stats["completed"] * 40 // stats["total"]
    stdout.write(u"\u2588" * (x - stats["progress"]))
    stdout.flush()
    return x


def startProgress():
    "Setup the template for progress tracker"

    stdout.write("Progress: [" + "_" * 40 + "]" + chr(8) * 41)
    stdout.flush()


def endProgress(stats):
    "Finish the progress tracker"

    stdout.write(u"\u2588" * (40 - stats["progress"]) + "]\n")
    stdout.flush()


def optimize_image(image_file_name, working_dir, max_dim, output_dir, stats):
    "Scale and optimize the given image"

    img = Image.open(working_dir + image_file_name)
    width, height = img.size

    if width > height:
        ratio = width / height
        new_width = max_dim if width > max_dim else width
        new_height = new_width // ratio
    else:
        ratio = height / width
        new_height = max_dim if height > max_dim else height
        new_width = new_height // ratio

    new_img = img.resize((int(new_width), int(new_height)), Image.ANTIALIAS)
    new_path = output_dir + "/" + image_file_name
    new_img.save(new_path, optimize=True, quality=75)

    # log stats
    stats["sizeBefore"] += os.path.getsize(working_dir + image_file_name)
    stats["sizeAfter"] += os.path.getsize(new_path)
    stats["completed"] += 1


# def is_gif_animated


init_script()
