import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Provide maven credentials in ~/.gradle/gradle.properties or using environment variables
val mavenUsername = System.getenv("MAVEN_REPO_USERNAME") ?: properties["mavenUsername"] as String?
val mavenPassword = System.getenv("MAVEN_REPO_PASSWORD") ?: properties["mavenPassword"] as String?

val mavenCredentials: PasswordCredentials.() -> Unit = {
    username = mavenUsername
    password = mavenPassword
}

// Getting the project's groupId, artifactId and version.
val buildGroupId = System.getenv("BUILD_GROUP_ID") ?: properties["groupId"] as String? ?: ""
val buildArtifactId = System.getenv("BUILD_ARTIFACT_ID") ?: properties["artifactId"] as String? ?: ""
val buildCommit = grgit.head().id?.substring(0..15) ?: "Unknown commit"
val buildNumber = System.getenv("BUILD_NUM") ?: "SNAPSHOT"

group = buildGroupId
version = buildNumber

plugins {
    kotlin("jvm") version "1.6.20"
    kotlin("plugin.serialization") version "1.6.10"

    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("org.ajoberstar.grgit") version "4.1.0"

    `maven-publish`
}

repositories {
    maven {
        url = uri("https://repo.astromc.gg/repository/maven-private/")
        credentials(mavenCredentials)
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation("com.charleskorn.kaml:kaml:0.43.0")
    implementation("com.github.CatDevz:EnvSchema:17470cf716")

    compileOnly("com.github.Minestom:Minestom:8b23af7896")

    implementation("net.kyori:adventure-text-minimessage:4.2.0-SNAPSHOT")
    implementation("com.github.Project-Cepi:KStom:9617367c18")

    testImplementation(kotlin("test"))
}

tasks {
    processResources {
        outputs.upToDateWhen { false }
        filesMatching("META-INF/extension.json") {
            expand(mapOf(
                "git" to buildCommit,
                "build" to buildNumber,
            ))
        }
    }

    shadowJar {
        archiveBaseName.set(project.name)
        minimize()
    }

    test {
        useJUnitPlatform()
    }

    build {
        dependsOn(shadowJar)
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
    freeCompilerArgs = listOf("-Xinline-classes", "-Xopt-in=kotlin.RequiresOptIn")
}
