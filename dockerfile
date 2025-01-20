FROM amazoncorretto:17
WORKDIR /app
COPY build/libs/datastore-app.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]