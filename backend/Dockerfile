FROM gradle:8-jdk21-alpine@sha256:3dd41aff1bf0421db1a094dbedd79ea18a55ec47e4b26f5d73625c7d0c88aa17 AS build

COPY gradle gradle
COPY gradlew build.gradle.kts settings.gradle.kts ./
RUN ./gradlew dependencies --no-daemon
COPY src src
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:23-jre-alpine-3.21@sha256:88593498863c64b43be16e8357a3c70ea475fc20a93bf1e07f4609213a357c87
COPY --from=build /home/gradle/build/libs/*.jar livechat.jar

EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=production
HEALTHCHECK --interval=30s --timeout=3s --retries=3 \
  CMD wget -q --spider http://localhost:8081/actuator/health || exit 1

ENTRYPOINT [ "java", "-jar", "livechat.jar" ]
