/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://jitpack.io")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}
val namedModules = configurations.create("namedModules")
val unnamedModules = configurations.create("unnamedModules")
tasks.compileJava {
    inputs.property("moduleName", "worldmanager.core")
    doFirst {
        options.compilerArgs = listOf(
            "--module-path", namedModules.asPath,
            "--add-modules", "org.json",
            "--patch-module", "worldmanager.core=${unnamedModules.asPath}"
        )

        classpath = files()
    }
}

//println(unnamedModules.asPath)
dependencies {
    api(libs.org.json.json)
   namedModules(libs.org.json.json)

    api(libs.com.github.querz.nbt)
    namedModules(libs.com.github.querz.nbt)
 unnamedModules(libs.com.github.querz.nbt)
    testImplementation(libs.org.junit.jupiter.junit.jupiter)
}

group = "com.thebest12lines"
version = "0.2.0"
description = "worldmanager"
java.sourceCompatibility = JavaVersion.VERSION_21

java {
    withSourcesJar()
  //  withJavadocJar()
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
