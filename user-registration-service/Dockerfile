FROM openjdk:10-jdk

ADD ./target/user-registration-service-0.0.1-SNAPSHOT.jar /usr/app/user-registration-service-0.0.1-SNAPSHOT.jar

WORKDIR /usr/app

ENTRYPOINT ["java","-jar", "user-registration-service-0.0.1-SNAPSHOT.jar"]
