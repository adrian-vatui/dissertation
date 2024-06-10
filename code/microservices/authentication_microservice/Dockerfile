FROM public.ecr.aws/amazoncorretto/amazoncorretto:21

COPY target/authentication_microservice-0.0.1-SNAPSHOT.jar authentication_microservice-0.0.1-SNAPSHOT.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/authentication_microservice-0.0.1-SNAPSHOT.jar"]