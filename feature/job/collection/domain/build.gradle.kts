plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.feature.job.data)
    implementation(projects.common.data)
    implementation(projects.storage)
    implementation(projects.common.domain)
    implementation(projects.common.utils)
    implementation(projects.network)
    implementation(libs.javax.inject)
}