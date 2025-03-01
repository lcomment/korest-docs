dependencies {
    implementation(libs.restdocs.core)
    implementation(libs.spring.web)
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
