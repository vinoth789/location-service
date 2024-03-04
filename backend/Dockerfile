FROM openjdk:17

WORKDIR /app

COPY target/location-0.0.1-SNAPSHOT.jar /app/location.jar

ENTRYPOINT ["java", "-jar", "location.jar"]