### The Request
To add customizable front-end scoring to a submission form that would give the entrant live feedback and could be updated by non technical admins (*muggles*).

### The Issue
This feature needed to be added to a system where there was no access to the underlying platform or form builder. The only options exposed through the form builder were:
- The type of field being added.
- That field's label (text label 1)
- That field's description (text label 2)
- CSS classes for the field section wrapper

### The Solution
To get this feature working and make it approachable by non-technical admins I had to leverage the primary and secondary labels on each field. This let the admins assign point values to each field, with special classes for Bonus items and Disqualifying items.

// TODO finish description

![Ui2YL5ABEC](https://user-images.githubusercontent.com/9448762/115936229-170b3b00-a46b-11eb-8a37-c85a84fffd77.gif)
