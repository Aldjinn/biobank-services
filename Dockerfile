FROM maven:3-jdk-11-slim AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn -Dmaven.artifact.threads=8 --no-transfer-progress package

FROM adoptopenjdk:11-jdk
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/dataservice-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-XX:+UseShenandoahGC", "-jar", "app.jar"]
