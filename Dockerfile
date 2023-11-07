# Utiliza una imagen base de OpenJDK 17
FROM openjdk:17

ADD ./target/my-application.jar my-application.jar

ENTRYPOINT ["java", "-jar", "my-application.jar"]