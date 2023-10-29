FROM azul/zulu-openjdk-alpine:17.0.7
EXPOSE 8080
COPY simplebanking/build/libs/simplebanking-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]