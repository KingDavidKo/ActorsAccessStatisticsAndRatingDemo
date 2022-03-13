# ActorsAccessStatisticsAndRatingDemo
Demo in Java, to enhance statistics and rating of actors for Actor’s Access website. Uses MVC, Rest API and Java Spring Boot.

This program is an extension for the actor's site Actor's Access, and offers a rating and statistics module. The actor's rating is computed based on points awarded from successful auditions, while the statistics aspect summarizes the actor's performance in a easy to follow display.

The program logs each audition completed.

Each audition has the following information: ID, Actor ID, Actor's First Name, Actor's Last Name, Actor's Email, Type of Activity, Project, Role, Activity Date, Creation Date (of the log), Number of days it took place, Agent Name, and Agent Email.

The program uses RestAPI to handle requests. The API allows the retrieval of auditions based on the Audition ID, Last name, First name, Actor ID, or all of the activities ever done from the database. There is a POST request for the actor to log the information, a PUT request to change an activity, and a DELETE request to delete their activity.

When a user completes an activity, they fill in the information required. The information is then stored in the database (Oracle). Whenever a log is modified in some way (added, updated, or deleted), an email is sent to the Actor's Email, displaying the information they need. At the bottom of the email are hyperlinks that direct the actor to view their dashboard, ratings, and statistics.

When viewing the statistics hyperlink, displayed are Pie Graphs showing the percentages of each of their activities (Audition, Recall, On Hold, and Booking). The percentages can be filtered by year. In the ratings section, a bar graph is displayed to show the point accumulation across the years. The graphs and webpages were illustrated using Google Charts and HTML.

A configuration text file was created so login information doesn not need to be changed within the code.
