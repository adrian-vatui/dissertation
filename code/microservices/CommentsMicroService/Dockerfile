FROM public.ecr.aws/amazoncorretto/amazoncorretto:21

COPY target/CommentsMicroService-0.0.1-SNAPSHOT.jar CommentsMicroService-0.0.1-SNAPSHOT.jar
EXPOSE 5004
ENTRYPOINT ["java","-jar","/CommentsMicroService-0.0.1-SNAPSHOT.jar"]