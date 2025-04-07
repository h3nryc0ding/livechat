FROM eclipse-temurin:24-jre-alpine-3.21@sha256:2b88976851406a7f185e9636656e578c8284a498dacaf77e1203a575e43cf84d AS build

COPY gradle gradle
COPY gradlew build.gradle.kts settings.gradle.kts ./
RUN ./gradlew dependencies --no-daemon
COPY src src
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:24-jre-alpine-3.21@sha256:2b88976851406a7f185e9636656e578c8284a498dacaf77e1203a575e43cf84d
COPY --from=build /build/libs/*.jar livechat.jar

EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=production
HEALTHCHECK --interval=30s --timeout=3s --retries=3 \
  CMD wget -q --spider http://localhost:8081/actuator/health || exit 1

ENTRYPOINT [ "java", "-jar", "livechat.jar" ]
