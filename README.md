#  Seminar Registration Platform
This is a web application designed to help users create a professional CV and register for seminars, which was tasked to us by WeLead for our third year in college internship. It is built with Java Spring on the backend and React on the frontend, and aims to integrate data collection and seminar registration into a seamless experience.


## Features
- User Registration and Login: Secure user authentication and authorization.
- Profile Management: Users can input and update personal information including: Name, Surname, Email, Phone Number, Education, Work Experience, Seminars Attended, Hobbies, Volunteering

- CV Generation: Automatically create a CV using the provided personal information.

- Seminar Registration: View available seminars and apply to them with a single click.

### Administrator Dashboard: 
Admins can:
- View user data in detailed tables
- Visualize data using graphs for better insights
- Manage seminars and user applications
## Tech Stack
- Backend: Java Spring Boot
- Frontend: React.js
- Database: MySQL
- Other: RESTful APIs, JWT Authentication
# Installation
### Prerequisites:
* Java 11 or higher
* Node.js and npm
* MySQL 
### Backend Setup
* Clone this repository
* Set up the database connection as you see fit. 
* Add new Spring boot configuration, using your IDE'S  java sdk version and the Demo1Application.java as the main class.

### Frontend Setup
* Navigate to the frontend directory: cd ./client
* npm install
* npm start



# Usage 
### User Workflow
* Register/Login: Users sign up or log in to the platform.
* Profile Completion: Users fill in their personal details.
* CV Generation: The platform generates a CV based on the provided data.
* Seminar Registration: Users can view and apply for seminars.
### Admin Workflow
* Login: Admins log in to access the dashboard.
* User Management: View user data in tables and graphs.
* Seminar Management: Create and manage seminar details and applications.
# Contributions
As this project was developed in collaboration with [Antonis Niotis](https://github.com/pet2ant/), work was split accordingly to each person's skillset. 
* I was responsible with creating the server side of the web app, while Antonis mainly focused with the front end.
* I was also responsible with implementing Spring security on the server side, along with access control and authentication.
* Antonis was in charge of developing the front-end in accordance to the design provided by WeLead, creating it according to their specifications, while also making sure of the site's responsiveness.
* We both worked together on the integration of the client and server side ( connecting endpoints and such), the creation of the CV builder, along with debugged the whole application. 
