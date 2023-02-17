FROM openjdk:8
EXPOSE 9090
ADD target/docker-poc.jar docker-poc.jar
ENTRYPOINT ["java","-jar","/docker-poc.jar"]