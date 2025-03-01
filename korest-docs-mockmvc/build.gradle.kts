dependencies {
    api(projects.core)
    api(libs.restdocs.mockmvc)
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
