FROM openjdk:10

ADD ./target/extractor-service-1.0.0.jar /usr/src/extractor-service-1.0.0.jar
EXPOSE 8067
WORKDIR usr/src
ENTRYPOINT ["java","-jar","extractor-service-1.0.0.jar"]