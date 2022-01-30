FROM openjdk:17
LABEL maintainer="abybekerov95@gmail.com"
EXPOSE 8080
ADD target/coffetime-0.0.1-SNAPSHOT.jar coffetime.jar
ENTRYPOINT ["java", "-jar", "/coffetime.jar"]