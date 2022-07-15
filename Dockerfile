FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/ms_Customer-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8001
ENTRYPOINT ["java","-jar","/app.jar"]