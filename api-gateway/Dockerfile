FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY . .
RUN apk add --no-cache maven
RUN mvn clean package -DskipTests
RUN cp target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]