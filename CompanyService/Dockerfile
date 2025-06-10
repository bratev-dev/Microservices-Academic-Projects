FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Instalar netcat para el wait-for-it.sh
RUN apk add --no-cache netcat-openbsd

COPY target/*.jar app.jar
COPY wait-for-it.sh ./wait-for-it.sh
RUN chmod +x wait-for-it.sh

EXPOSE 8081
ENTRYPOINT ["./wait-for-it.sh", "postgres", "5432", "--", "java", "-jar", "app.jar"]  