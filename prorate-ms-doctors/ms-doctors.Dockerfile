FROM openjdk:20-slim-buster
RUN mkdir /app
WORKDIR /app
COPY target/prorate-ms-doctors-0.0.1-SNAPSHOT.jar doctors.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","doctors.jar", "--spring.profiles.active=docker", "--config-server=config-server"]