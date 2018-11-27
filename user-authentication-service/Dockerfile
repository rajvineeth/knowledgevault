FROM openjdk:8

ADD ./target/user-authentication-service-1.0.0.jar /usr/app/user-authentication-service-1.0.0.jar

EXPOSE 8184

WORKDIR /usr/app

ENTRYPOINT ["java","-jar", "user-authentication-service-1.0.0.jar"]
