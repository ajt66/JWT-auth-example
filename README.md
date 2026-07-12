# JWT-auth-example

A simple project to demonstrate JWT authentication in spring boot.

---

# Usage

* Users can register using their username, email and password through the appropriate endpoint.
* The Token used for registering is returned as a response.
* Registered users can also login using their username and password through the appropriate endpoint.
* The Token used for login is returned as a response.

---

## Tech Stack

* Backend
  * Java 25 - core programming language
  * [Maven](https://maven.apache.org/) - Dependency management
  * [Spring Boot](https://spring.io) - Framework
  * [Java JWT](https://github.com/jwtk/jjwt) - Library for creating and verifying JSON Web Token (JWT)
  * [PostgreSql](https://www.postgresql.org/) - Relational Database

---

# Pre-Requisites

* Java 21 or higher
* Maven 3.5.14 or higher
* Postman (NOTE: curl command currently not working.)

---

# Starting the application

## Jar assembly

Run the following command in your terminal to assemble the jar file for the application

* For Maven wrapper in VS code :
```sh
./mvnw clean package
```
or
* For Maven :
```sh
mvn clean package
```

Then, run the following command in your terminal
```sh
java -jar target/jwt_authentication-0.0.1-SNAPSHOT.jar
```

## Through Maven/Maven Wrapper

Run the following command in your terminal to run the application
```sh
./mvnw spring-boot:run
```
or

Run the following command in your terminal to run the application
```sh
mvn spring-boot:run
```

---

## Example workflow

(NOTE: curl command currently not working.)

* Registering Through the Postman application :

Open the postman app, select "POST" method in the dropdown menu and type the following link in the URL field :
```sh
localhost:8080/api/auth/register
```

then, open the body tab below the URL field. Select the "raw" option and the "JSON" option from the dropdown menu beside the binary option. Fill the request body in a similar format to the one below :
```sh
{"username":"abc","email":"abc@xyz.com","password":"secret234"}
```

The API will generate and return a JWT in the response body:
```sh
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb3NoIiwiaWF0IjoxNzgzNDI2NTAzLCJleHAiOjE3ODM1MTI5MDN9.CaeG1lQ6bog2NlajWMkQY8RoPcokGpbHFtQXt-gMp9E"
}
```

* Logging in through Postman :

* Registering Through the Postman application :

Open the postman app, select "POST" method in the dropdown menu and type the following link in the URL field :
```sh
localhost:8080/api/auth/login
```

Then, follow the instructions in the previous section and fill the request body in a similar format to the one below :
```sh
{"username":"abc","password":"secret234"}
```

The API will generate and return a JWT in the response body:
```sh
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb3NoIiwiaWF0IjoxNzgzNDI2NTg2LCJleHAiOjE3ODM1MTI5ODZ9.M42EwfdVgR5GxfhE2exnrJmSgSfUNxpiZtDQLXrwQ4Q"
}
```

---