FROM openjdk:14
RUN mkdir cliente
COPY . /cliente
WORKDIR /cliente
EXPOSE 7000
ENTRYPOINT ["java", "-jar", "dist/ClienteNodoP1.jar"]
