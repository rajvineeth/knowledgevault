FROM openjdk:8
ADD ./target/web-scraping-1.0.0.jar  /usr/src/web-scraping-1.0.0.jar
EXPOSE 8100
WORKDIR /usr/src
ENTRYPOINT ["java","-jar", "web-scraping-1.0.0.jar"]
