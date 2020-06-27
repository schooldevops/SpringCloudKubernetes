FROM openjdk:14-jdk-alpine
VOLUME /tmp
RUN apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/app
ADD @project.build.finalName@.jar /usr/local/app/
WORKDIR /usr/local/app
CMD java -jar @project.build.finalName@.jar --server.port=8081
