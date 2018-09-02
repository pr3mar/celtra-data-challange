FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD target/rest-1.0-SNAPSHOT.jar /app

EXPOSE 80

CMD ["java", "-jar", "rest-1.0-SNAPSHOT.jar"]
