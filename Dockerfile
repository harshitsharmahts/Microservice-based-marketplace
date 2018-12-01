FROM openjdk:8
ADD target/Cart.jar Cart.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar", "Cart.jar"]
