FROM openjdk:15-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/errorlocalization-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
