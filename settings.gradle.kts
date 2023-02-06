rootProject.name = "minesweeper"
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }

    plugins {
        kotlin("jvm") version "1.8.0"
    }
}
include(
    "core",
    "desktop",
    "android",
    "ios"
)