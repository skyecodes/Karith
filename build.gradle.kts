import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version "2.0.21"
    id("app.cash.burst") version "2.1.0"
    id("dev.mokkery") version "2.5.1"
    id("org.jetbrains.dokka") version "1.9.20"
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    idea
    signing
    `maven-publish`
}

group = "com.skyecodes"
version = "0.2.0"

repositories {
    mavenCentral()
}

kotlin {
    withSourcesJar()
    jvm()
    linuxX64()
    linuxArm64()
    mingwX64()
    wasmJs {
        browser()
        nodejs()
    }
    wasmWasi {
        nodejs()
    }

    sourceSets {
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

tasks.withType<KotlinCompile> {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_21)
}

tasks.withType<DokkaTask>().configureEach {
    moduleName.set("Karith")
    dokkaSourceSets["commonMain"].apply {
        includes.from("$projectDir/docs/kdoc-module.md")
    }
}

/*publishing {
    publications {
        create<MavenPublication>("karith") {
            from(components["java"])
            pom {
                name.set("Karith")
                description.set("Kotlin Arithmetic Parser")
                url.set("https://github.com/skyecodes/Karith")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://mit-license.org/")
                    }
                }
                developers {
                    developer {
                        id.set("skyecodes")
                        name.set("skyecodes")
                        email.set("skyecodeso@proton.me")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/skyecodes/Karith.git")
                    developerConnection.set("scm:git:https://github.com/skyecodes/Karith.git")
                    url.set("https://github.com/skyecodes/Karith")
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
}*/