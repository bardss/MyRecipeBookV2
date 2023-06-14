plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("myrecipebook.android.library.jvmversion")
    id("myrecipebook.android.library.sdk")
}

android {
    namespace = "com.jakubaniola.database"
}

dependencies {

    // Unit Testing
    testImplementation(testlibs.bundles.junit)
}