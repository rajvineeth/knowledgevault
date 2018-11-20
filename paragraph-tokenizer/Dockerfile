FROM openjdk:8

ADD ./target/paragraph-tokenizer-1.0.0.jar   /usr/src/paragraph-tokenizer-1.0.0.jar
EXPOSE 8065
WORKDIR usr/src
ENTRYPOINT ["java","-jar","paragraph-tokenizer-1.0.0.jar"]