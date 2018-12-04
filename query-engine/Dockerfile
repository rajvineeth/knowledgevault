FROM openjdk:8
ADD ./target/query-engine-1.0.0.jar   /usr/src/query-engine-1.0.0.jar
EXPOSE 8185
WORKDIR usr/src
ENTRYPOINT ["java","-jar","query-engine-1.0.0.jar"]
