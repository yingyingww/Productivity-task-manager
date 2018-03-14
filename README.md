# Productivity+: A New Kind of Task Manager
### By Andrew Biehl, Eva Grench, Calypso Leonard, and Yingying Wang

## About 

Imagine how awkward it would feel to quarter off time to "eat breakfast" every day on your calendar app, or set a reminder to "sleep" on your task manager. Well now you have a place to do those things! Set your daily schedule goals and compare your real-time activities side-by-side. Receive tips and feedbacks, keep track of how productive you feel and enjoy a visual representation of your daily schedule. Productivity+ can do it all!

Productivity+ is your new go-to daily schedule manager. Combining the visuals of a calendar app with the check-box functionality of a normal task manager, you can now track everything you do during your day, from brushing your teeth to working out. Productivity+ currently has 2 pages, a main page on which the user's activity is recorded throughout the day, and a Set Schedule page, where users can input planned activities for the upcoming day. There is a menu bar on top of the window that allows the users to navigate between pages and provides instruction to new users. Users can choose to exit the app anytime be selecting exit from the menu bar. 

## Instructions 

The app is started by running the main method in our Main.java class. This is currently located in "Productivity+/src/finalProject". A few days ago we ran into the issue of our contributers using different versions of Java with IntelliJ. If there are SDK errors, it is important that in the project structure (File --> Project Structure), the two middle drop down menus are set to the save version of Java (we've been using 1.8/8 and 9.0/9).

Running the main method of Main.java brings you to the Set Schedule page where you can create your schedule for the day. Before you do that though, feel free to read through the instructions for Productivity+ to help you get a feel for what we can help you do! After you set your schedule, you can navigate back to main either by clicking "Use Schedule" or the Main Page option in the navigation menu in the top left.

## Known bugs 

We started this final project with pretty big goals in mind. As we anticipated, we were not able to finish everything we wanted to do with this project. We were able to get it to a place where all of the basic functions are possible.

Nothing the user does will shut down the app, but command line errors will currently occur when the productivity tips and the top five tasks buttons in the drop down menu are clicked. We had big plans for these, but unfortunately did not get around to storing data long term, which is necessary to make these parts function correctly.

Also, the times along the left sides of the calendars on both pages is currently slight "off" in that towards the end of the day, the tasks appear to be starting slightly before and ending slightly after when the user said they wanted them to. We did not get around to fixing this.

We were not able to provide the functionality for resizing the window in a way that looked good and made sense to the user. If that is done, weird gray spaces show up and the height of the tasks on the calendars do not adjust.

## Things to keep working on

Productivity+ is a work in progress and we have lots of ideas of where this could go in the future. Firstly, we would love to implement the ability for users to edit their schedules and tasks. This could look like the ability to delete tasks when making an ideal schedule, changing the time manually of tasks on the current schedule, allowing for multitasking, etc. Additionally, we think there are a lot of potential uses for keeping track of this data long term and analyzing it in an interesting way. While we started heading in that direction, we were not able to fully implement it. Lastly, we think it would be great to offer the users the ability to select their ideal schedule from pre-made templates (e.g. the user has templates for each day of the week) and then allow them to edit them to fit their day.
