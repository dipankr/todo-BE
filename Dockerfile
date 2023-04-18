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
COPY --from=build /target/*.jar app.jar
COPY --from=build /application-prod.properties application-prod.properties
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","app.jar"]
