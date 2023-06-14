plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("myrecipebook.android.library.jvmversion")
    id("myrecipebook.android.library.sdk")
    id("kotlin-kapt")
}

android {
    namespace = "com.jakubaniola.database"
}

dependencies {
    // Modules
    implementation(project(":model"))

    // Room
    implementation(room.runtime)
    implementation(room.ktx)
    kapt(di.compiler)
    annotationProcessor(di.compiler)

    // Unit Testing
    testImplementation(testlibs.bundles.junit)
}