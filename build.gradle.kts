import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("org.jetbrains.dokka") version "1.7.10"
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    idea
    signing
    `maven-publish`
}

group = "dev.franckyi"
version = "0.1.0"

val mockkVersion: String by properties

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("io.mockk:mockk:$mockkVersion")
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.javadoc {
    enabled = false
}

val dokkaHtml by tasks.getting(DokkaTask::class) {
    moduleName.set("Karith")
    dokkaSourceSets["main"].apply {
        includes.from("$projectDir/docs/kdoc-module.md")
    }
}

tasks.replace("javadocJar", Jar::class.java).apply {
    dependsOn(dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaHtml.outputDirectory)
}

publishing {
    publications {
        create<MavenPublication>("karith") {
            from(components["java"])
            pom {
                name.set("Karith")
                description.set("Kotlin Arithmetic Parser")
                url.set("https://github.com/Franckyi/Karith")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://mit-license.org/")
                    }
                }
                developers {
                    developer {
                        id.set("Franckyi")
                        name.set("Franck Velasco")
                        email.set("franck.velasco@hotmail.fr")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/Franckyi/Karith.git")
                    developerConnection.set("scm:git:https://github.com/Franckyi/Karith.git")
                    url.set("https://github.com/Franckyi/Karith")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["karith"])
}


nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}