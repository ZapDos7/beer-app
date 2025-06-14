# A Spring Boot Beer Viewing and Rating App

A simple Spring Boot app which displays a list of beers, information about them and allows the user to rate them. Supports user registration, authentication, search and rate functionality.

## Instructions

1. Clone the repository to your local machine. Ensure you have **Java 17**, **Maven**, and **MySQL** installed.
2. Update the database credentials in `application.yml`.
3. Run the app using maven with `mvn spring-boot:run` or using your preferred IDE.
4. While the app is running, you can:
    1. Access the app's endpoints [via browser](http://localhost:8080), `cURL`, or any API client of your choice. </br>A list of available endpoints (as JSON) can be found [here](https://github.com/ZapDos7/beer-app/blob/main/src/main/resources/postman.json).
    2. Explore the interactive API documentation via [Swagger](http://localhost:8080/swagger-ui.html) for more info.
    3. Check the application's health status using the [Actuator health check endpoint](http://localhost:8080/actuator/health)

## API Definition

### Public Endpoints (do not require log in)
1. View all beers
2. View a beer's details
3. Sign up 
4. Log in

### User Endpoints (require log in)
1. View all user's ratings
2. Add/Edit rating to beer
3. Delete rating of beer
4. View account info

### Admin Endpoints (require log in & admin rights)
1. Add a new beer
2. Edit an existing beer's info
3. Delete a beer
4. View all users
5. Delete a user

## DB Schema

![DB Schema](https://github.com/ZapDos7/beer-app/blob/feature/reboot/src/main/resources/schema.png "DB Schema")

## Technologies

* Used [JWT](https://jwt.io/) for authentication ([read here](https://www.unlogged.io/post/integrating-jwt-with-spring-security-6-in-spring-boot-3))

## To Dos
- [ ] Introduce docker [1](https://spring.io/guides/gs/spring-boot-docker), [2](https://www.baeldung.com/dockerizing-spring-boot-application), [3](https://docs.docker.com/guides/docker-overview/)
- [ ] Implement FE [react](https://www.freecodecamp.org/news/full-stack-development-with-mongodb-java-and-react/) or [node](https://www.freecodecamp.org/news/learn-full-stack-development-html-css-javascript-node-js-mongodb/)
- [ ] [Deploy to AWS](https://www.freecodecamp.org/news/how-to-deploy-your-freecodecamp-project-on-aws/)

### Support
For comments, ideas, issues or general talks about this project, feel free to [open an issue](https://github.com/ZapDos7/beer-app/issues/new/choose). 
