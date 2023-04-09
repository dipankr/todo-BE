#
# Build stage
#
FROM maven:3.9-amazoncorretto-20 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:20-slim-buster
COPY --from=build /target/todo-BE-0.1-SNAPSHOT.jar app.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
