FROM openjdk:10
ADD ./target/NLPService-1.0.0.jar  /usr/src/NLPService-1.0.0.jar
EXPOSE 8080
WORKDIR /usr/src
ENTRYPOINT ["java","-jar", "NLPService-1.0.0.jar"]
