import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.vanniktech.maven.publish)
}

dependencies {
    api(projects.core)
    api(projects.mockmvc)
    implementation(libs.spring.web)
    implementation(libs.spring.test)
    implementation(libs.restdocs.restassured)
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
        artifactId = "${property("artifact")}-starter",
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
