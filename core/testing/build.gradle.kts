@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("myrecipebook.android.library.jvmversion")
    id("myrecipebook.android.library.sdk")
    alias(di.plugins.plugin).apply(false)
}

android {
    namespace = "com.jakubaniola.testing"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    // Libraries
    api(androidx.core.ktx)
    api(platform(external.kotlin.bom))
    api(testlibs.bundles.androidx)
    api(testlibs.bundles.kotlinx)
    api(testlibs.bundles.junit)
    api(testlibs.mockk)
}