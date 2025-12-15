FROM amazoncorretto:17

WORKDIR /app

EXPOSE 8080

ADD target/demo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]