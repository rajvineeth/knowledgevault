FROM openjdk:8

ADD ./target/populator-1.0.0.jar /usr/src/populator-1.0.0.jar
EXPOSE 8067
WORKDIR usr/src
ENTRYPOINT ["java","-jar","populator-1.0.0.jar"]