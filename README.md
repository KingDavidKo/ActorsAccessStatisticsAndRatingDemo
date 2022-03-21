# Actors Access Statistics And Rating Demo
Demo in Java, to enhance statistics and rating of actors for Actor’s Access casting website that connects actors and casting agencies alike. Uses MVC, Rest API and Java Spring Boot. The webpages and charts were illustrated using HTML with embedded Javascript statements and Google Charts. The project was deployed and run on Apache Tomcat 9.0.36 application server. The demo video you can find in the uploaded file O2DuoActorAccessDemo.mp4.

This program is an extension for Actor's Access site and offers an actor's rating and statistics module. The actor's rating is computed based on points awarded from successful auditions, while the statistics aspect summarizes the actor's performance in an easy-to-follow display. The project contains five primary modules:

1. Actor Rating and Statistics Rest API Specification
2. Actor's activity persistence
3. Actor Rating and Statistics Computation
4. Email notifications
5. Actor Dashboard, Rating and Statistics presentation

When an actor completes an audition, they fill in the information required. The information is then stored in the database (Oracle). The program logs each audition completed.

Each audition has the following information: ID, Actor ID, Actor's First Name, Actor's Last Name, Actor's Email, Type of Activity, Project, Role, Activity Date, Creation Date (of the log), Number of days it took place, Agent Name, and Agent Email.

The program uses Rest API to handle requests. The API allows the retrieval of auditions based on the Audition ID, Last name, First name, Actor ID, or all of the activities ever done from the database. There is a POST request for the actor to log the information, a PUT request to change an activity, and a DELETE request to delete their activity.

Whenever a record is modified in some way (added, updated, or deleted), a notification email is sent to the Actor, Admin, Actor's Agent (or any other pre-configured email), displaying the information they need. At the bottom of the email are hyperlinks that direct the user to view their Dashboard, Ratings and Statistics.

When viewing the Statistics hyperlink, displayed are Pie Chart showing the percentages of each of their completed activities (Audition, Recall, On Hold and Booking). The percentages can be filtered by year. In the Ratings section, a Bar Chart is displayed to show the point accumulation across the years. The Dashboard page illustrates the overall actor's performance in comparison to others based on the collected points.

Configuration/Configurations.txt file can be updated, so login/DB/email information does not need to be changed within the code.
