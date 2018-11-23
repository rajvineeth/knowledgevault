FROM openjdk:8
ADD ./target/document-identifier-1.0.0.jar  /usr/src/document-identifier-1.0.0.jar
EXPOSE 8083
WORKDIR /usr/src
ENTRYPOINT ["java","-jar", "document-identifier-1.0.0.jar"]
