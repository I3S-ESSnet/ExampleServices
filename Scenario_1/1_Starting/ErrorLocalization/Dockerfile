FROM maven:3.6.3-openjdk AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:15-jdk-alpine
ARG JAR_FILE

COPY --from=build /usr/src/app/target/errorlocalization-0.0.1-SNAPSHOT.jar /usr/app/app.jar

ENTRYPOINT ["java","-jar","/usr/app/app.jar"]
