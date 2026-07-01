FROM maven:3.9.16-amazoncorretto-25-alpine AS build

WORKDIR /app

COPY pom.xml .
COPY config-server/pom.xml config-server/pom.xml
RUN mvn -B -ntp dependency:go-offline -f config-server/pom.xml

COPY config-server/src config-server/src
RUN mvn clean package -B -ntp -DskipTests -Dmaven.source.skip=true -Dmaven.javadoc.skip=true -f config-server/pom.xml

FROM eclipse-temurin:25-jre-alpine

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app

COPY --from=build --chown=appuser:appgroup /app/config-server/target/*.jar app.jar

EXPOSE 8888
USER appuser

ENTRYPOINT ["java", "-jar", "app.jar"]