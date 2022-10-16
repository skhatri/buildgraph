gradle clean build exportDependency -I init.gradle
rm build/libs/*plain.jar
docker build --no-cache -t buildgraph .
