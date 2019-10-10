import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
val vertxVersion: String by project

plugins {
    java
    kotlin("jvm") version "1.3.50"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.vertx:vertx-core:${vertxVersion}")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    testCompile("junit", "junit", "4.12")
}

tasks.create<JavaExec>("run") {
    main=project.properties.getOrDefault("mainClass", "com.example.HelloVerticle") as String
    classpath = sourceSets["main"].runtimeClasspath
    systemProperties["vertx.logger-delegate-factory-class-name"] = "io.vertx.core.logging.SLF4JLogDelegateFactory"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}