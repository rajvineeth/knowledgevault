FROM openjdk:8

ADD ./target/zuulgateway-1.0.0.jar /usr/src/zuulgateway-1.0.0.jar
EXPOSE 8081
WORKDIR usr/src
ENTRYPOINT ["java","-jar","zuulgateway-1.0.0.jar"]