FROM openjdk:17
EXPOSE 8080:8080
ADD target/soil-microservice.jar soil-microservice.jar
ENTRYPOINT ["java","-jar","/soil-microservice.jar"]