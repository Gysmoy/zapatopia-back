FROM java:17
EXPOSE 8080
ADD /target/zapatopia-web.jar zapatopia-web.jar
ENTRYPOINT ["java","-jar","zapatopia-web.jar"]
