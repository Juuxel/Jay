import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.11"
    maven
}

group = "juuxel.jay"
version = "0.1.0"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    api(kotlin("stdlib-jdk8"))
    api("com.github.anymaker:tnjson:1.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testImplementation("io.strikt:strikt-core:0.17.1")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.2.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
