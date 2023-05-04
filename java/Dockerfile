# Build step
FROM 7-jdk17 AS build

COPY gradle.properties /app/gradle.properties
COPY settings.gradle.kts /app/settings.gradle.kts
COPY build.gradle.kts /app/build.gradle.kts
COPY src /app/src
RUN gradle build -Dquarkus.package.type=uber-jar

# Package step
FROM eclipse-temurin:17-jre-jammy
COPY --from=build /app/build/voltaired-1.0.0-SNAPSHOT-runner.jar /app/app.jar

LABEL authors="vinetos"

EXPOSE 8080

RUN groupadd -g 999 appuser && \
    useradd -r -u 999 -g appuser appuser
USER appuser

COPY --chown=appuser:appuser --chmod=444 /app/build/*-runner.jar /app/app.jar

ENTRYPOINT ["java","-jar","app.jar"]


