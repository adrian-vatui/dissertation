FROM public.ecr.aws/amazoncorretto/amazoncorretto:21

COPY target/ImagesMicroservice-0.0.1-SNAPSHOT.jar ImagesMicroservice-0.0.1-SNAPSHOT.jar
EXPOSE 5006
ENTRYPOINT ["java","-jar","/ImagesMicroservice-0.0.1-SNAPSHOT.jar"]