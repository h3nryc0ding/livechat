import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.spring") version "2.0.0"

    id("com.netflix.dgs.codegen") version "6.2.1"

    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:8.6.0")
    }
}

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    // Testing
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Misc
    implementation("com.netflix.graphql.dgs:graphql-dgs-webflux-starter")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("spring.profiles.active", "test")
}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
    generateClient = true
    generateKotlinClosureProjections = true
    generateKotlinNullableClasses = true
    packageName = "opensource.h3nryc0ding.livechat.generated"
    language = "kotlin"
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    version = "1.0.0"
    filter {
        include("**/src/**/*.kt", "**.kts")
        exclude("**/build/**/*.kt", "**/generated/**/*.kt")
    }
}
