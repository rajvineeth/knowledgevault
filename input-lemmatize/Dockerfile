FROM openjdk:8
ADD ./target/input-lemmatize-1.0.0.jar   /usr/src/input-lemmatize-1.0.0.jar
EXPOSE 8140
WORKDIR usr/src
ENTRYPOINT ["java","-jar","input-lemmatize-1.0.0.jar"]