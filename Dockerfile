FROM openjdk:8
ADD target/LoginSignup.jar LoginSignup.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar", "LoginSignup.jar"]
