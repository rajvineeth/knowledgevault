FROM openjdk:10-jdk

ADD ./target/user-authentication-service-0.0.1-SNAPSHOT.jar /usr/app/user-registration-service-0.0.1-SNAPSHOT.jar

EXPOSE 8084

WORKDIR /usr/app

ENTRYPOINT ["java","-jar", "user-authentication-service-0.0.1-SNAPSHOT.jar"]
