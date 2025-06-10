plugins {
    kotlin("jvm") version "2.1.20"
    id("fabric-loom") version "1.10.1"
}

group = "cz.lukynka.mods"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(libs.bundles.fabric)
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}