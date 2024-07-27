FROM openjdk:21

WORKDIR /usr/src/myapp

COPY target/user-svc-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]