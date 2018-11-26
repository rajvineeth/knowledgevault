FROM openjdk:8

ADD ./target/user-registration-service-1.0.0.jar /usr/app/user-registration-service-1.0.0.jar

EXPOSE 8182

WORKDIR /usr/app

ENTRYPOINT ["java","-jar", "user-registration-service-1.0.0.jar"]

