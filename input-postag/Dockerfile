FROM openjdk:8
ADD ./target/input-postag-1.0.0.jar   /usr/src/input-postag-1.0.0.jar
EXPOSE 8142
WORKDIR usr/src
ENTRYPOINT ["java","-jar","input-postag-1.0.0.jar"]