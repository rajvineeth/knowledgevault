FROM openjdk:8

ADD ./target/input-processor-1.0.0.jar /usr/src/input-processor-1.0.0.jar
EXPOSE 8093
WORKDIR usr/src
ENTRYPOINT ["java","-jar","input-processor-1.0.0.jar"]