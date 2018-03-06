# Productivity+: A New Kind of Task Manager
### By Andrew Biehl, Eva Grench, Calypso Leonard, and Yingying Wang

Productivity+ is your new go-to daily schedule manager. Combining the visuals of a calendar app with the check-box functionality of a normal task manager, you can now track everything you do during your day, from brushing your teeth to working out.

Imagine how awkward it would feel to quarter off time to "eat breakfast" every day on your calendar app, or set a reminder to "sleep" on your task manager. Well now you have a place to do those things! Set your daily schedule goals and compare your real-time activities side-by-side. Receive tips and feedback, keep track of how productive you feel and export your data for other applications. Productivity+ can do it all!

*Please note: Productivity+ is a work in progress, and most functionalities have not been fully implemented yet. Thank you for your patience!*

Productivity+ currently has two pages, a main page on which the user's activity is recorded throughout the day, and a Set Schedule page, where users can input planned activities for the upcoming day.

There are some interactions between our various pages and pop-ups which are currently handled within the View files, but will be handled by the Controller once it has been implemented. Much of the information given by the user is simply displayed either in the terminal or in the current window for now, this is to indicate that the information is being noted. Once the controller and model are implemented, this information will be passed to the relevant function within the model.
The lack of controller also means that several of our functions are listed as public at the moment, which is required for our pages to interact. 
Once we have completed the controller, we will focus on ensuring proper encapsulation.
The 'Trends' tab will eventually lead to a page analyising productivity trends over time, however, because the format of this data presentation depends on details of out model's method of data handling, we decided that it would be counterproductive to create a dummy viewer for this page until the model is in place.
