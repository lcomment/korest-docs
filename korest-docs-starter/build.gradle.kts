dependencies {
    api(projects.core)
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
