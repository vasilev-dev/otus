plugins {
    id("build-jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":fastify-api-v1-jackson")) // todo delete
    implementation(project(":fastify-common"))

    testImplementation(kotlin("test-junit"))
}