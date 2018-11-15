FROM openjdk:10

ADD ./target/config-server-0.0.1-SNAPSHOT.jar /usr/src/config-server-0.0.1-SNAPSHOT.jar
EXPOSE 8092
WORKDIR usr/src
ENTRYPOINT ["java","-jar","config-server-0.0.1-SNAPSHOT.jar"]