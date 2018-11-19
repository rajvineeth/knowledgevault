FROM openjdk:8

ADD ./target/extractor-service-1.0.0.jar /usr/src/extractor-service-1.0.0.jar

EXPOSE 8094
WORKDIR usr/src
ENTRYPOINT ["java","-jar","extractor-service-1.0.0.jar"]
