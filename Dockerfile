FROM amazoncorretto:11-alpine-jdk

WORKDIR /app

COPY build/libs/transaction-mgmt-0.0.1-SNAPSHOT.jar  /app

CMD ["java", "-jar", "transaction-mgmt-0.0.1-SNAPSHOT.jar"]

