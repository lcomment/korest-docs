import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.vanniktech.maven.publish)
}

dependencies {
    api(projects.core)
    api(libs.restdocs.mockmvc)
    implementation(libs.jakarta.servlet.api)
    implementation(libs.spring.context)
    implementation(libs.junit.jupiter)
    implementation(libs.jackson.databind)
    implementation(libs.jackson.datatype.jsr310)
    implementation(libs.jackson.core)

    testImplementation(libs.kotest.junit)
    testImplementation(libs.kotest.assertions.core)
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}

mavenPublishing {
    coordinates(
        groupId = "${property("group")}",
        artifactId = "${property("artifact")}-mockmvc",
        version = "${property("version")}"
    )

    pom {
        name.set("korest-docs")
        description.set("Spring Restdocs extension library using Kotlin Dsl")
        inceptionYear.set("2025")
        url.set("https://github.com/lcomment/korest-docs")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("lcomment")
                name.set("Hyunseok Ko")
                email.set("komment.dev@gmail.com")
            }
        }

        scm {
            connection.set("scm:git:git://github.com/lcomment/korest-docs.git")
            developerConnection.set("scm:git:ssh://github.com/lcomment/korest-docs.git")
            url.set("https://github.com/lcomment/korest-docs.git")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()
}
