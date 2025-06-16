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
1. Beers:
   * Add a new beer
   * Edit an existing beer's info
   * Delete a beer
2. Users:
   * View all users
   * Delete a user
3. Countries:
   * Get all countries
   * Add a country
   * Delete a country

## DB Schema

![DB Schema](https://github.com/ZapDos7/beer-app/blob/feature/reboot/src/main/resources/schema.png "DB Schema")

## Technologies

* Used [JWT](https://jwt.io/) for authentication
* Used Redis for country management (since they're only stored as a `String`, this was a simple and effective solution, more light-weight than adding another SQL table.)
* Used OpenAPI for documentation
* Used Bruno for testing
* Used JUnit & Mockito for testing

## To Dos
- [ ] Introduce docker
- [ ] Implement FE
>>>>>>> 845423b (added country management API)

### Support
For comments, ideas, issues or general talks about this project, feel free to [open an issue](https://github.com/ZapDos7/beer-app/issues/new/choose). 
