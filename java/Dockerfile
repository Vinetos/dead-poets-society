# Build step
FROM gradle:7-jdk17 AS build

WORKDIR /app

COPY gradle.properties gradle.properties
COPY settings.gradle.kts settings.gradle.kts
COPY build.gradle.kts build.gradle.kts
COPY src ./

RUN gradle build -Dquarkus.package.type=uber-jar --no-daemon

# Package step
FROM eclipse-temurin:17-jre-jammy

LABEL authors="vinetos"

EXPOSE 8080

RUN groupadd -g 999 appuser && \
    useradd -r -u 999 -g appuser appuser
USER appuser

COPY --chown=appuser:appuser --chmod=444 --from=build /app/build/*-runner.jar /app/app.jar

ENTRYPOINT ["java","-jar","app.jar"]


