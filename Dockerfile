FROM openjdk:8
ADD target/Db-User.jar Db-User.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar", "Db-User.jar"]
