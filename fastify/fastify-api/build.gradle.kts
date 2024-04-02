plugins {
    id("build-jvm")

}

dependencies {
    implementation("dev.vasilev.fastify.libs:fastify-lib-logging-common")
    implementation("dev.vasilev.fastify.libs:fastify-lib-logging-logback")

    implementation(libs.coroutines.core)
}