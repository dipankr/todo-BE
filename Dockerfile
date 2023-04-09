FROM openjdk:19-slim-buster
VOLUME /tmp
ARG JAR_FILE


COPY $JAR_FILE app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]