FROM public.ecr.aws/amazoncorretto/amazoncorretto:21

COPY target/PostsMicroservice-0.0.1-SNAPSHOT.jar PostsMicroservice-0.0.1-SNAPSHOT.jar
EXPOSE 5002
ENTRYPOINT ["java","-jar","/PostsMicroservice-0.0.1-SNAPSHOT.jar"]