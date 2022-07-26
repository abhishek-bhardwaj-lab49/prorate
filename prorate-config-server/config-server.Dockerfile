FROM openjdk:20-slim-buster
RUN mkdir /app
WORKDIR /app
COPY target/prorate-config-server-0.0.1-SNAPSHOT.jar configs.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","configs.jar", "--spring.profiles.active=docker"]