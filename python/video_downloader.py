import urllib.request
import sys
import threading

EPISODES = []
THREADS = []
STORY_ARCS = {
    39: "The Saiyan Saga",
    74: "Namek and Captain Ginyu Saga",
    107: "Frieza Saga",
    117: "Garlic Jr. Saga",
    139: "Android Saga",
    165: "Cell Saga",
    194: "Cell Games Saga",
    219: "World Tournament Saga",
    253: "Majin Buu Saga",
    291: "Fusion and Kid Buu Saga"
}


class Thread(threading.Thread):
    "Create new thread"
    def __init__(self, thread_id):
        threading.Thread.__init__(self)
        self.thread_id = thread_id

    def run(self):
        global EPISODES
        while EPISODES:
            download_episode(EPISODES.pop(0))



def report(downloaded, block_size, file_size):
    "progress updater"
    progress = ((downloaded * block_size) / file_size) * 100
    progress = str(int(progress)) + "%"
    sys.stdout.write("\r" + progress)
    sys.stdout.flush()


# URL = "http://107.155.78.122/~saiyanwatch2/Movies/018.mp4"
# URL = "http://107.155.78.122/~saiyanwatch2/Dragon%20Ball%20Z/001.m4v"
# FILE = "C:\\Users\\c_her\\Videos\\Dragon Ball Z\\DBZ_01.mp4"

# for count in range(40, 75):
def download_episode(count):

    super_url = "http://107.155.78.122/~sw3/Dragon%%20Ball%%20Super/%s.mp4"
    super_path = "C:\\Users\\c_her\\Videos\\Dragon Ball Super\\DBS_%s.mp4"

    z_url = "http://107.155.78.122/~saiyanwatch2/Dragon%%20Ball%%20Z/%s.m4v"
    z_path = "C:\\Users\\c_her\\Videos\\Dragon Ball Z\\DBZ_%s.m4v"

    episode = str(count).zfill(3)

    url = super_url % episode
    file = super_path % episode
    print("Downloading Episode #" + str(count))
    urllib.request.urlretrieve(url, file, report)
    print("\nComplete")
    print("-" * 20)


for x in range(1, 70):
    EPISODES.append(x)

for x in range(2):
    THREADS.append(Thread(x))

for thread in THREADS:
    thread.start()

