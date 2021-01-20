plugins {
    kotlin("multiplatform") version "1.4.21"
    id("dev.fritz2.fritz2-gradle") version "0.8"
    kotlin("plugin.serialization") version "1.4.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

kotlin {
    /* Targets configuration omitted. 
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
    js {
        browser {
            runTask {
                devServer = org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.DevServer(
                    port = 9000,
                    contentBase = listOf("$buildDir/distributions"),
                    proxy = mapOf(
                        "/api" to "http://localhost:8080"
                    )
                )
            }
        }
    }


    sourceSets {

        val serializationVersion = "1.0.1"
        val ktorVersion = "1.5.0"
        val logbackVersion = "1.2.3"
        val exposedVersion = "0.28.1"
        val h2Version = "1.4.200"

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-netty:$ktorVersion")
                implementation("io.ktor:ktor-serialization:$ktorVersion")
                implementation("io.ktor:ktor-auth:$ktorVersion")
                implementation("io.ktor:ktor-gson:$ktorVersion")
                implementation("ch.qos.logback:logback-classic:$logbackVersion")
                implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
                implementation("com.h2database:h2:$h2Version")
            }
        }
    }
}