FROM openjdk:8
ADD ./target/input-tagger-1.0.0.jar   /usr/src/input-tagger-1.0.0.jar
EXPOSE 8144
WORKDIR usr/src
ENTRYPOINT ["java","-jar","input-tagger-1.0.0.jar"]