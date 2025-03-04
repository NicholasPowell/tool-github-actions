import com.niloda.build.extensions.Extensions.Companion.sources
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_1
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
    id("com.niloda.build-extensions") version "1.0-SNAPSHOT"
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.jetbrains.kotlin.plugin.spring") version "2.1.10"
}

group = "com.niloda.github"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
}

tasks.test { useJUnitPlatform() }

tasks.jar {
    manifest { attributes["Main-Class"] = "com.niloda.github.tool.ToolKt" }
}

sources {
    val api by create
    val spi by create {
        implementation dependsOn "io.arrow-kt:arrow-core-jvm:2.0.1"
    }
    val integration by create {
        implementation dependsOn spi
        implementation dependsOn "org.springframework.boot:spring-boot-starter"
        implementation dependsOn "org.springframework.boot:spring-boot-starter-web"
        implementation dependsOn "org.springframework.boot:spring-boot-starter-webflux"
        implementation dependsOn "io.arrow-kt:arrow-core-jvm:2.0.1"
        implementation dependsOn "io.arrow-kt:arrow-integrations-jackson-module:0.15.1"
        implementation dependsOn "com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2"
        implementation dependsOn "org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0"

        implementation dependsOn "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
        implementation dependsOn "org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.4"
        implementation dependsOn "io.ktor:ktor-client-core:2.3.8"
        implementation dependsOn "io.ktor:ktor-client-cio:2.3.8"

        implementation dependsOn "io.netty:netty-resolver-dns-native-macos:4.1.107.Final:osx-aarch_64"
    }

    val main by getting {
        implementation dependsOn api
        implementation dependsOn spi
        implementation dependsOn integration
    }
    val test by getting {
        implementation dependsOn api
        implementation dependsOn spi
        implementation dependsOn integration
    }

    val confirmTest by create {
        implementation dependsOn api
        implementation dependsOn spi
        implementation dependsOn integration
    }

    dependencies {
        implementation(kotlin("stdlib"))
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
        implementation("org.jetbrains.kotlin:kotlin-reflect:2.1.0")
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-web")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}

tasks.withType<KotlinCompilationTask<*>> {
    compilerOptions {
        apiVersion.set(KOTLIN_2_1)
        languageVersion.set(KOTLIN_2_1)
        optIn.add("kotlin.RequiresOptIn")
        progressiveMode.set(true)
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xcontext-receivers")
    }
}
