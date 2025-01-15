# event-scheduler-system
The Event Scheduler System is...

## 📑 Table of Contents
- [Features](#features-mvp)
- [Technologies](#technologies)
- [Installation](#installation-and-usage)
- [Objectives](#learning-objectives)
- [Roadmap](#roadmap)

### Features (MVP)
#### Event Management
- Create, edit and delete events
- View a list of available events

#### User Registration
- User registration and authorization
- Roles: Admin, participant

#### Event Registration
- Sign up for events
- View registered events

### Technologies
- **Frontend**: React.js
- **Backend**: Java with Spring
- **Database**: PostgreSQL
- **Authentication**: JSON Web Tokens (JWT)
- **Testing**: JUnit
- **Tools**: Docker

### Installation and Usage
#### Backend
1. Clone the repository
```
git clone https://github.com/your-username/event-scheduler-system.git
cd event-scheduler-system
```
2. Set up the environment:  
  Create an **.env** file with database connection details (username, password...)
3. Run the Spring Boot application
```
./mvnw spring-boot:run
```

#### Frontend
1. Navigate to the frontend directory
```
cd frontend
```
2. Install dependencies and run the development server
```
npm install
npm start
```


### Learning Objectives
- Design and develop a complete system from scratch.
- Integrate a modern frontend with a robust backend.
- Apply best development practices (Git, CI/CD, testing).
- Deploy the application to a production environment.

### Roadmap
- [ ] Design the database schema.
- [ ] Build the backend (REST API).
  - [ ] Implement authentication and authorization.
- [ ] Create the frontend with React.js.
- [ ] Deploy to a production server (Heroku or AWS).
