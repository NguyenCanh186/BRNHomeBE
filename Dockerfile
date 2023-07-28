FROM openjdk:8-jdk-alpine
ADD target/*.jar blogBE.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/blogBE.jar"]
