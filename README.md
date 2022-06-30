# Word Count API
The Word count allows the following operations
1.	method that allows you to add one or more words
2.	method that returns the count of how many times a given word was added to the word counter

# Getting Started
### For building and running the application you need:

- JDK 17
- Maven 

### Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.synechron.wordcount.WordCountApplication class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

    mvn spring-boot:run

## Endpoints

- Get /wordCount/{word}
- Post /wordCount/{word}

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.0-M3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.0-M3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.0-M3/reference/htmlsingle/#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

