FROM gradle:8-jdk21-alpine@sha256:3dd41aff1bf0421db1a094dbedd79ea18a55ec47e4b26f5d73625c7d0c88aa17 AS build

COPY gradle gradle
COPY gradlew build.gradle.kts settings.gradle.kts ./
RUN ./gradlew dependencies --no-daemon
COPY src src
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:21.0.6_7-jre-alpine-3.21@sha256:4e9ab608d97796571b1d5bbcd1c9f430a89a5f03fe5aa6c093888ceb6756c502
COPY --from=build /home/gradle/build/libs/*.jar livechat.jar

EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=production
HEALTHCHECK --interval=30s --timeout=3s --retries=3 \
  CMD wget -q --spider http://localhost:8081/actuator/health || exit 1

ENTRYPOINT [ "java", "-jar", "livechat.jar" ]
