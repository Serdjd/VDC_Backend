# Usa una imagen base con Maven y JDK
FROM maven:3.9.6-eclipse-temurin-21 as build

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos del proyecto (código, pom.xml, etc.)
COPY . .

# Compila y construye el .jar usando Maven
RUN mvn clean package -DskipTests

# Usa una imagen más ligera solo con JDK para ejecutar la app
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el .jar construido desde la etapa anterior
COPY --from=build /app/target/VDC_Backend-0.0.1-SNAPSHOT.jar /app/myapp.jar

# Expone el puerto usado por la aplicación
EXPOSE 9094

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "myapp.jar"]
