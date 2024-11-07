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
    id("jacoco")
}

tasks.register("setup") {
    doLast {
        val dirs = listOf(
            "worldmanager",
            "worldmanager/features",
            "worldmanager/objects",
        )
        dirs.forEach { dir ->
            mkdir(dir)
        }
        val zipUrl = URI("https://thebest12dev.github.io/resources/worldmanager/objects.zip").toURL();
        val zipFile = file("worldmanager/main.zip")
        val outputDir = file("worldmanager/objects")

        // Download the zip file
        zipUrl.openStream().use { input ->
            Files.copy(input, zipFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
        }

        // Unzip the file
        ZipInputStream(zipFile.inputStream()).use { zipStream ->
            var entry = zipStream.nextEntry
            while (entry != null) {
                val filePath = File(outputDir, entry.name)
                if (entry.isDirectory) {
                    filePath.mkdirs()
                } else {
                    filePath.parentFile.mkdirs()
                    FileOutputStream(filePath).use { output ->
                        zipStream.copyTo(output)
                    }
                }
                zipStream.closeEntry()
                entry = zipStream.nextEntry
            }

        }
        Files.delete(Paths.get(System.getProperty("user.dir")+"/worldmanager/main.zip"));
    }
}
tasks.named("clean") {
    dependsOn("setup")
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

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}


jacoco {
    toolVersion = "0.8.7"
}



tasks.test {
    useJUnitPlatform()
}

