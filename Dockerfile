FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copiamos archivos
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw

# Asignar permisos de ejecución a maven wrapper
RUN chmod +x mvnw

# Desactivar MAVEN_CONFIG 
ENV MAVEN_CONFIG=""

# Descargamos las dependencias
RUN ./mvnw dependency:resolve dependency:resolve-plugins

# Copiar los archivos del proyecto
COPY src/ ./src

# Construir el proyecto
RUN ./mvnw clean package -DskipTests

# Usamos la imagen de eclipse-temurin:21-jre-alpine
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiar el JAR generado
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto
EXPOSE 8080

# Ejecutar el proyecto
ENTRYPOINT ["java", "-jar", "app.jar"]