plugins {
    kotlin("plugin.spring") apply false
    kotlin("plugin.jpa") apply false
    id("org.springframework.boot") apply false
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

val asciidoctorExt: Configuration by configurations.creating
val snippetsDir by extra { file("build/generated-snippets") }

dependencies {
    implementation(projects.mockmvc)
    implementation(libs.spring.boot.starter.web)

    testImplementation(libs.spring.boot.starter.test)

    asciidoctorExt(libs.restdocs.asciidoctor)
}

tasks.asciidoctor {
    inputs.dir(snippetsDir)
    configurations(asciidoctorExt.name)
    dependsOn(tasks.test)

    sources {
        include("**/index.adoc")
    }

    baseDirFollowsSourceFile()

    doFirst {
        delete {
            file("src/main/resources/static/docs")
        }
    }
}

tasks.register("copyHtml", Copy::class) {
    group = "documentation"
    description = "Copies generated AsciiDoc HTML files to the static/docs directory."

    dependsOn(tasks.asciidoctor)
    from(file("build/docs/asciidoc/"))
    into(file("src/main/resources/static/docs"))
}

tasks.build {
    dependsOn(tasks.getByName("copyHtml"))
}

tasks.bootJar {
    dependsOn(tasks.asciidoctor)
    dependsOn(tasks.getByName("copyHtml"))
}

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}
