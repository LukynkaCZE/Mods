plugins {
    id("java")
    kotlin("jvm") version "2.1.20"
}

group = "cz.lukynka.mods"
version = "1.0"


subprojects {
    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(21)
    }

    repositories {
        mavenCentral()
    }
}

repositories {
    mavenCentral()
}