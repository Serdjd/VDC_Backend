# Usa una imagen base de Java
FROM openjdk:21-jdk-slim

# Setea la ruta de trabajo
WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/VDC_Backend-0.0.1-SNAPSHOT.jar /app/myapp.jar

# Expone el puerto que usará la aplicación
EXPOSE 9094

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "myapp.jar"]
