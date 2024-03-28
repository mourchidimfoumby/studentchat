plugins {
    id("org.gradle.kotlin.kotlin-dsl") version "4.3.1"
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    implementation("com.android.tools.build:gradle:8.3.1")
}