FROM openjdk:8
ADD ./target/input-tokenize-1.0.0.jar   /usr/src/input-tokenize-1.0.0.jar
EXPOSE 8046
WORKDIR usr/src
ENTRYPOINT ["java","-jar","input-tokenize-1.0.0.jar"]