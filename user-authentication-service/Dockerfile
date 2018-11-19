FROM openjdk:10-jdk

ADD ./target/user-authentication-service-1.0.0-SNAPSHOT.jar/usr/app/user-registration-service-1.0.0-SNAPSHOT.jar

EXPOSE 8084

WORKDIR /usr/app

ENTRYPOINT ["java","-jar", "user-authentication-service-1.0.0-SNAPSHOT.jar"]
