# A Spring Boot Beer Viewing and Rating App

A simple Spring Boot app which displays a list of beers, information about them and allows the user to rate them. Supports user registration, authentication, search and rate functionality.

## How to Run this Project
1. Clone the project `git clone git@github.com:ZapDos7/beer-app.git`
2. Run the application `mvn spring-boot:run` 
3. Open your favourite browser & navigate to: `http://localhost:8080/`

### To Dos
 - [ ] Introduce get user details endpoint (for users) 
 - [ ] (re-)Introduce tests
 - [ ] Introduce [security](https://www.baeldung.com/registration-with-spring-mvc-and-spring-security) or [security](https://www.toptal.com/java/rest-security-with-jwt-spring-security-and-java)
 - [ ] Introduce docker
 - [ ] Implement FE

### API Definition

#### Public Controller (do not require log in)
1. View all beers
2. View a beer's details

#### User Controller (require log in)
1. View all user's ratings
2. Add/Edit rating to beer
3. Delete rating of beer

#### Admin Controller (require log in & admin rights)
1. Add a new beer
2. Edit an existing beer's info
3. Delete a beer
4. View all users
5. Delete a user

[Postman collection](https://github.com/ZapDos7/beer-app/blob/feature/reboot/src/main/resources/postman.json)

### DB Schema

![DB Schema](https://github.com/ZapDos7/beer-app/blob/feature/reboot/src/main/resources/schema.png "DB Schema")

### License
[MIT](https://opensource.org/licenses/MIT)

### Support
For comments, ideas, issues or general talks about this project, feel free to [open an issue](https://github.com/ZapDos7/beer-app/issues/new/choose). 
