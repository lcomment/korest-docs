rootProject.name = "korest-docs"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.6.0"
}

// example
module(name = ":example", path = "example")

// korest docs
module(name = ":core", path = "korest-docs-core")
module(name = ":starter", path = "korest-docs-starter")
module(name = ":mockmvc", path = "korest-docs-mockmvc")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

fun module(name: String, path: String) {
    include(name)
    project(name).projectDir = file(path)
}
