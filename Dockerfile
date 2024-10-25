FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/temp-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app.jar"]
