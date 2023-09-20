FROM openjdk:17
WORKDIR /eazylogg-server
COPY target/eazylogg-server.jar /eazylogg-server/eazylogg-server.jar
ENTRYPOINT ["java", "-jar", "eazylogg-server.jar"]