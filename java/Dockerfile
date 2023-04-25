FROM registry.cri.epita.fr/forge/formation/formation-interne/registry/eclipse-temurin:17

LABEL authors="vinetos"

EXPOSE 8080

RUN groupadd -g 999 appuser && \
    useradd -r -u 999 -g appuser appuser
USER appuser

WORKDIR /app
COPY --chown=appuser:appuser --chmod=444 target/*-runner.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
