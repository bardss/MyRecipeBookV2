plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("myrecipebook.android.library.jvmversion")
    id("myrecipebook.android.library.sdk")
    id("kotlin-kapt")
    alias(di.plugins.plugin).apply(false)
}

android {
    namespace = "com.jakubaniola.repository"
}

dependencies {
    // Data sources
    implementation(project(":datalayer:datasource:database"))
    implementation(project(":model"))
    implementation(project(":core:common"))

    implementation(kotlinx.coroutines.android)
    implementation(di.bundles.hilt)
    kapt(di.compiler)

    // Unit Testing
    testImplementation(testlibs.bundles.junit)
}

kapt {
    correctErrorTypes = true
}