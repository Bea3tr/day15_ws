FROM eclipse-temurin:23-jdk

LABEL maintainer=bea3tr

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn

COPY pom.xml .
COPY src src

# RUN chmod a+x ./mvnw && ./mvnw package -Dmaven.test.skip=true
RUN ./mvnw package -Dmaven.test.skip=true

ENV SERVER_PORT=8080
EXPOSE ${SERVER_PORT}

ENTRYPOINT java -jar target/day15_ws-0.0.1-SNAPSHOT.jar