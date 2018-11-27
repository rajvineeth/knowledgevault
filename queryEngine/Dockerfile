FROM openjdk:8
ADD ./target/queryEngine-0.0.1-SNAPSHOT.jar   /usr/src/queryEngine-0.0.1-SNAPSHOT.jar
EXPOSE 8185
WORKDIR usr/src
ENTRYPOINT ["java","-jar","queryEngine-0.0.1-SNAPSHOT.jar"]