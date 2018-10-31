FROM openjdk:10

ADD ./target/paragraph-summarization-service-0.0.1-SNAPSHOT.jar /usr/src/paragraph-summarization-service-0.0.1-SNAPSHOT.jar
EXPOSE 8090
WORKDIR usr/src
ENTRYPOINT ["java","-jar","paragraph-summarization-service-0.0.1-SNAPSHOT.jar"]