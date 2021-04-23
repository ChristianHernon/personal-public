"CSS picker script"

import re
import os

class CssRule:
    "Class for a single CSS Rule"

    def __init__(self, css):
        self.tags = css.split("{", 1)[0]
        self.properties = "{" + css.split("{", 1)[1]

    def compile_rule(self):
        "Concat to build complete css rule"
        return self.tags + self.properties
    # end CssRule


def parse_css():
    "Read in and parse the css file"

    parsed_file = []
    in_comment = False
    curr_rule = ""
    open_brace_count = 0
    close_brace_count = 0

    with open("C:/Users/IBM_ADMIN/Documents/Code Practice/bootstrap.min.css") as file:
        for line in file:
            for index, character in enumerate(line):
                try:
                    if character == "/" and line[index + 1] == "*":
                        in_comment = True
                    elif character == "/" and line[index - 1] == "*":
                        in_comment = False
                    elif not in_comment:
                        if character == "{":
                            open_brace_count += 1
                            curr_rule += character
                        elif character == "}":
                            close_brace_count += 1
                            curr_rule += character
                            if close_brace_count == open_brace_count:
                                parsed_file.append(CssRule(curr_rule))
                                curr_rule = ""
                                open_brace_count = 0
                                close_brace_count = 0
                        else:
                            curr_rule += character
                except IndexError:
                    pass

    return parsed_file
    # end parse_css()


def parse_html():
    "Read in and parse the html file"

    file = open("C:/Users/IBM_ADMIN/Documents/Code Practice/test_page.html")
    html_code = file.read()
    file.close()

    html_matches = re.findall(r"<\w+|class=[\"'][a-zA-Z0-9- ]+[\"']", html_code)
    used_tags_and_classes = []

    # remove duplicates
    html_matches = list(set(html_matches))

    # strip matches down to class names and tag names
    while html_matches:
        tag = html_matches.pop()
        # print(tag)
        if re.match(r"^<\w+", tag):
            tag = tag.replace("<", "")
            used_tags_and_classes.append(tag)
        else:
            tag = re.sub(r"class=|[\"']", "", tag)
            classes = tag.split(' ')
            for _class in classes:
                used_tags_and_classes.append("." + _class)

    return used_tags_and_classes
    # end parse_html()


def optimize_css():
    "Pick out needed rules"

    used_html = parse_html()
    css_rules = parse_css()
    needed_rules = []
    media_queries = []

    global USED_HTML
    USED_HTML = used_html

    for rule in css_rules:
        if "@media" in rule.tags and ".container" in rule.properties:
            media_queries.append(rule)
        for tag in used_html:
            pattern = re.compile(r"(^" + tag + r",?)|([,{\s]" + tag + r",?)")
            if "@media" not in rule.tags and re.search(pattern, rule.tags):
                needed_rules.append(rule.compile_rule())
                break
            elif "@media" in rule.tags and re.search(pattern, rule.properties) or "*" in rule.properties:
                needed_rules.append(rule.compile_rule())
                break
            elif "*" in rule.tags:
                needed_rules.append(rule.compile_rule())
                break
    return needed_rules
    # end optimize_css()


def save_css(css_rules):
    "Save the picked CSS rules to a file"

    curr_dir = os.getcwd()
    file_obj = open(curr_dir + "\\new_css.css", "w")
    file_obj.write(css_rules.replace("\n", ""))
    file_obj.close()

    return None
    # end save_css()


CSS = optimize_css()
RULES_COUNT = len(CSS)
CSS = "\n".join(CSS)
# print(CSS)
# print(USED_HTML)
# print(RULES_COUNT)
save_css(CSS)
