@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("myrecipebook.android.library.jvmversion")
    id("myrecipebook.android.library.sdk")
    id("myrecipebook.android.library.compose")
    alias(di.plugins.plugin).apply(false)
}

android {
    namespace = "com.jakubaniola.recipedetails"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Modules
    implementation(project(":designsystem"))
    implementation(project(":common"))
    implementation(project(":datalayer:repository"))
    implementation(project(":model"))

    // Libraries
    implementation(androidx.core.ktx)
    implementation(androidx.bundles.compose)
    implementation(platform(external.kotlin.bom))
    implementation(platform(androidx.compose))
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation(di.bundles.hilt)
    kapt(di.compiler)

    // Unit Testing
    testImplementation(testlibs.bundles.junit)

    // UI testing
    androidTestImplementation(testlibs.junit.ext)
    androidTestImplementation(testlibs.espresso.core)
    androidTestImplementation(platform(androidx.compose))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // Debug
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

kapt {
    correctErrorTypes = true
}
