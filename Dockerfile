FROM openjdk:8
ADD target/Inventory.jar Inventory.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar", "Inventory.jar"]
