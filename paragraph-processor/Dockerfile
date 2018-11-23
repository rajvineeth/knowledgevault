FROM openjdk:8

ADD ./target/paragraph-processor-1.0.0.jar /usr/src/paragraph-processor-1.0.0.jar
EXPOSE 8433
WORKDIR usr/src
ENTRYPOINT ["java","-jar","paragraph-processor-1.0.0.jar"]