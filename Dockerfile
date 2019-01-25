FROM openjdk:8u171-jdk-alpine3.7

CMD ["/usr/bin/java", "-jar", "/falcon-data.jar"]

COPY target/app.jar /falcon-data.jar