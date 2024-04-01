plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.kotlin.serialization.get().pluginId)
    id(libs.plugins.apollo.grapghql.get().pluginId).version(libs.versions.apollographql)
}

apollo {
    service("service") {
        packageName.set("com.basapps.seekdemo.network")
    }
}



dependencies {
    implementation(projects.storage)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.auth)
    implementation(libs.apollo.grapghql.runtime)
}