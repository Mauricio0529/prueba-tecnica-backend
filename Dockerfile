# Utiliza una imagen base de OpenJDK 17
FROM openjdk:17

# Directorio de trabajo en el contenedor
WORKDIR /app

RUN maven clean target

FROM openjdk:17

# Copia el archivo JAR de la aplicación al contenedor
COPY target/my-application.jar .

# Expone el puerto en el que la aplicación está escuchando
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/my-application.jar"]