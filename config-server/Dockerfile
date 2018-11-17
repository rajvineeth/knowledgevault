FROM openjdk:10

ADD ./target/config-server-1.0.0.jar /usr/src/config-server-1.0.0.jar
EXPOSE 8092
WORKDIR usr/src
ENTRYPOINT ["java","-jar","config-server-1.0.0.jar"]