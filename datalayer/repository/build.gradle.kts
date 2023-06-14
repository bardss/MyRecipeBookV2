plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("myrecipebook.android.library.jvmversion")
    id("myrecipebook.android.library.sdk")
}

android {
    namespace = "com.jakubaniola.repository"
}

dependencies {
    // Data sources
    implementation(project(":datalayer:datasource:database"))
    implementation(project(":model"))

    implementation(kotlinx.coroutines.android)

    // Unit Testing
    testImplementation(testlibs.bundles.junit)
}