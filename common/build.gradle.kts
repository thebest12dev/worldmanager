import java.io.FileOutputStream
import java.net.URI
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.zip.ZipInputStream


/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    `java-library`
    `maven-publish`
    java
}

repositories {
    mavenLocal()
    mavenCentral()
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
            "--add-modules", "org.json,jopt.simple",
            "--patch-module", "worldmanager.core=${unnamedModules.asPath}"
        )

        classpath = files()
    }
}

//println(unnamedModules.asPath)
dependencies {
    implementation("net.sf.jopt-simple:jopt-simple:5.0.4")
    namedModules("net.sf.jopt-simple:jopt-simple:5.0.4")
    implementation(libs.org.json.json)
    namedModules(libs.org.json.json)

    implementation(libs.com.github.querz.nbt)
    namedModules(libs.com.github.querz.nbt)
    unnamedModules(libs.com.github.querz.nbt)
    testImplementation(libs.org.junit.jupiter.junit.jupiter)

}

group = "com.thebest12lines"
version = "0.2.0"
description = "worldmanager"
java.sourceCompatibility = JavaVersion.VERSION_17

java {
    withSourcesJar()
    //  withJavadocJar()
}


tasks.withType<Jar> { archiveBaseName.set("worldmanager.common")}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "com.thebest12lines"
            artifactId = "worldmanager.common"
            version = "0.3.0"
        }
    }
    repositories {
        maven {
            url = uri("file://${buildDir}/repo")
        }
    }

}




tasks.test {
    useJUnitPlatform()
}
