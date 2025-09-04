import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.20"
    id("fabric-loom") version "1.10.1"
}

group = "cz.lukynka.mods"
version = parent!!.version
val targetJavaVersion = 21

repositories {
    mavenCentral()
    maven("https://mvn.devos.one/releases")
    maven("https://mvn.devos.one/snapshots")
}

base {
    archivesName.set("common")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
    withSourcesJar()
}

dependencies {
    modImplementation(project(":common"))
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())

    modImplementation(libs.bundles.fabric)
    modImplementation(libs.fabricLanguageKotlin)
    implementation(libs.tide)
    modImplementation(libs.yacl)
    modImplementation(libs.modmenu)
}

tasks.processResources {
    inputs.property("version", project.version)
    inputs.property("minecraft_version", libs.minecraft.get().version)
    inputs.property("loader_version", libs.fabricLoader.get().version)
    filteringCharset = "UTF-8"

    filesMatching("fabric.mod.json") {
        expand(
            "version" to project.version, "minecraft_version" to libs.minecraft.get().version, "loader_version" to libs.fabricLoader.get().version, "kotlin_loader_version" to libs.fabricLanguageKotlin.get().version
        )
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(targetJavaVersion)
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions.jvmTarget.set(JvmTarget.fromTarget(targetJavaVersion.toString()))
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}