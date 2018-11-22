FROM openjdk:8
ADD ./target/input-kafka-1.0.0.jar   /usr/src/input-kafka-1.0.0.jar
EXPOSE 8048
WORKDIR usr/src
ENTRYPOINT ["java","-jar","input-kafka-1.0.0.jar"]