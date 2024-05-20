FROM java:17
EXPOSE 8080
ADD /target/zapatopia-web-0.0.1-SNAPSHOT.jar zapatopia-web.jar
ENTRYPOINT ["java","-jar","zapatopia-web.jar"]
