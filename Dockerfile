FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
 ARG JAR_FILE=*/target/beyond-bookmarks-api.jar
 COPY ${JAR_FILE} app.jar
 ENTRYPOINT ["java","-jar","/app.jar"]
