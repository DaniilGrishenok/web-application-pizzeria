FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/web-application-pizzeria-0.0.1-SNAPSHOT.jar /app/webapp-pizzeria.jar

COPY src/main/resources/pizzawebapp-267bb-firebase-adminsdk-oi1n5-45cc57fdff.json /app/src/main/resources/pizzawebapp-267bb-firebase-adminsdk-oi1n5-45cc57fdff.json

CMD ["java", "-jar", "webapp-pizzeria.jar"]