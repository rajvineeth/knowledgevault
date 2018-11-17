FROM openjdk:10

ADD ./target/eureka-registry-1.0.0.jar /usr/src/eureka-registry-1.0.0.jar
EXPOSE 8066
WORKDIR usr/src
ENTRYPOINT ["java","-jar","eureka-registry-1.0.0.jar"]