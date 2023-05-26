# Build step
FROM gradle:7-jdk17 AS build

RUN gradle --version && java -version

WORKDIR /app

# Only copy dependency-related files
COPY build.gradle.kts gradle.properties settings.gradle.kts /app/

# Only download dependencies
# Eat the expected build failure since no source code has been copied yet
RUN gradle clean build --no-daemon > /dev/null 2>&1 || true

COPY src src

RUN gradle clean build -Dquarkus.package.type=uber-jar -DskipTests=true --no-daemon

# Package step
FROM eclipse-temurin:17-jre-jammy

LABEL authors="vinetos"

EXPOSE 8080

RUN groupadd -g 999 appuser && \
    useradd -r -u 999 -g appuser appuser
USER appuser

COPY --chown=appuser:appuser --chmod=444 --from=build /app/build/*-runner.jar /app/app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]


