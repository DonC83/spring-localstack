import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.1.0"
    id("io.gitlab.arturbosch.detekt").version("1.20.0")
    kotlin("jvm") version "1.8.10"
    kotlin("plugin.spring") version "1.7.10"
}

group = "com.donc"

java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.21.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("software.amazon.awssdk:dynamodb:2.17.254")
    implementation("software.amazon.awssdk:dynamodb-enhanced:2.18.5")
    implementation("software.amazon.awssdk:dynamodb-enhanced:2.17.254")
    implementation("software.amazon.awssdk:secretsmanager:2.26.0")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

detekt {
    toolVersion = "1.20.0"
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
}
