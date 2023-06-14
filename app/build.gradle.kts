@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    alias(di.plugins.plugin)
}

android {
    namespace = "com.jakubaniola.myrecipebook"
    compileSdk = 33

    defaultConfig {
        applicationId  = "com.jakubaniola.myrecipebook"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
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
    implementation(project(":feature:recipeslist"))

    // Libraries
    implementation(androidx.core.ktx)
    implementation(androidx.lifecycle.runtime.ktx)
    implementation(androidx.activity.compose)
    implementation(platform(external.kotlin.bom))
    implementation(platform(androidx.compose))
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation(di.android)
    implementation(di.navigation)
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
