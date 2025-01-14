import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "3.3.4"
  id("io.spring.dependency-management") version "1.1.6"
  kotlin("jvm") version "1.9.25"
  kotlin("plugin.spring") version "1.9.25"
  kotlin("plugin.jpa") version "1.9.25"
  kotlin("kapt") version "1.9.25"
}

group = "com.dongdong"
version = "0.0.1-SNAPSHOT"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(17)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

  implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
  kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")

  runtimeOnly("com.mysql:mysql-connector-j")

  testImplementation("com.h2database:h2")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("io.kotest:kotest-runner-junit5:5.6.2")
  testImplementation("io.kotest:kotest-assertions-core:5.6.2")
  testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
  testImplementation("io.kotest.extensions:kotest-extensions-testcontainers:2.0.2")
  implementation(kotlin("script-runtime"))
}

kotlin {
  compilerOptions {
    freeCompilerArgs.addAll("-Xjsr305=strict")
  }
}

allOpen {
  annotation("jakarta.persistence.Entity")
  annotation("jakarta.persistence.MappedSuperclass")
  annotation("jakarta.persistence.Embeddable")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += "-Xjsr305=strict"
    jvmTarget = "17"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

// build 시 jar 파일 하나만 생성되도록 조치
tasks.named<Jar>("jar") {
  enabled = false
}
