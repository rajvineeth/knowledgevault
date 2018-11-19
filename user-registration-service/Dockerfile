FROM openjdk:10-jdk

ADD ./target/user-registration-service-1.0.0-SNAPSHOT.jar /usr/app/user-registration-service-1.0.0-SNAPSHOT.jar

EXPOSE 8082

WORKDIR /usr/app

ENTRYPOINT ["java","-jar", "user-registration-service-0.0.1-SNAPSHOT.jar"]

