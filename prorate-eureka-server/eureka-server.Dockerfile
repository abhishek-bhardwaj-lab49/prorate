FROM openjdk:20-slim-buster
RUN mkdir /app
WORKDIR /app
COPY target/prorate-eureka-server-0.0.1-SNAPSHOT.jar eureka.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","eureka.jar","--spring.profiles.active=docker"]