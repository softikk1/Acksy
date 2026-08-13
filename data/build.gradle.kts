plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.acksy.entities.jvm)

    implementation("androidx.datastore:datastore-preferences:1.2.1")

    implementation(libs.kotlinx.datetime)

    implementation(libs.ktor.client.resources)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.kotlinx.serialization.core)
}