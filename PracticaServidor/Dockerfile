FROM openjdk:14
RUN mkdir /servidor
COPY . /servidor
EXPOSE 7000
WORKDIR /servidor
ENTRYPOINT ["java", "-jar", "dist/PracticaServidor.jar"]

