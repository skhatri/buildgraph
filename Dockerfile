FROM cloudnativek8s/microservices-java17-alpine:v1.0.16

COPY build/libs/buildgraph-*.jar /opt/app/app.jar

EXPOSE 8080
CMD ["-jar", "/opt/app/app.jar"]
