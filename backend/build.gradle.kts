plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.spring") version "2.1.21"
	
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
	
    id("com.netflix.dgs.codegen") version "7.0.3"
    id("org.jlleitschuh.gradle.ktlint") version "12.2.0"
}

group = "opensource.h3nryc0ding"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:10.0.4")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter")
    testImplementation("com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter-test")

    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    testImplementation("io.projectreactor:reactor-test")
	
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("spring.profiles.active", "test")
}

tasks.generateJava {
    packageName = "opensource.h3nryc0ding.livechat.generated"
    generateClient = true
    generateKotlinClosureProjections = true
    generateKotlinNullableClasses = true
}

ktlint {
    version = "1.5.0"
    filter {
        include("**/src/**/*.kt", "**.kts")
        exclude("**/build/**/*.kt", "**/generated/**/*.kt")
    }
}
