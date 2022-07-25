FROM openjdk:20-slim-buster
RUN mkdir /app
WORKDIR /app
COPY target/prorate-ms-reviews-0.0.1-SNAPSHOT.jar reviews.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","reviews.jar", "--spring.profiles.active=docker"]