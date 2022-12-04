import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.7.20"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "dev.debuggings"
version = "1.0"

repositories {
    maven("https://jitpack.io")
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<ShadowJar> {
    manifest {
        attributes(
            "Main-Class" to "dev.debuggings.aoc2022.MainKt"
        )
    }
}
