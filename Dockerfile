FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
 ARG JAR_FILE=target/tinyurl-0.0.1-SNAPSHOT.jar
 COPY ${JAR_FILE} app.jar
 ENTRYPOINT ["java","-jar","/app.jar"]
