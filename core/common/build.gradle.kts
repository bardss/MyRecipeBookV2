plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("myrecipebook.android.library.jvmversion")
    id("myrecipebook.android.library.sdk")
    id("kotlin-kapt")
}

android {
    namespace = "com.jakubaniola.common"
}

dependencies {
    // DI
    implementation(di.bundles.hilt)
    kapt(di.compiler)

    // Unit Testing
    testImplementation(testlibs.bundles.junit)
}