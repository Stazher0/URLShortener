FROM maven:3.9-eclipse-temurin-26 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests -B

FROM eclipse-temurin:26-jre-alpine
WORKDIR /app

RUN apk add --no-cache wget

RUN addgroup -S appgroup && adduser -S appuser -G appgroup
RUN mkdir -p /tmp/urlshortener /app/logs \
    && chown -R appuser:appgroup /tmp/urlshortener \
    && chown -R appuser:appgroup /app

COPY --from=build /app/target/*.jar app.jar
RUN chown -R appuser:appgroup /app && chmod -R 755 /app

USER appuser

EXPOSE 8081

ENV JAVA_OPTS="-Djava.io.tmpdir=/tmp/urlshortener -XX:MaxRAMPercentage=75.0 -XX:+UseZGC -Djava.security.egd=file:/dev/./urandom"

HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8081/actuator/health || exit 1

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]