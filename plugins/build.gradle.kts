import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.jakubaniola.plugins"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:8.0.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.22")
}

gradlePlugin {
    plugins {
        register("libraryJvmVersionPlugin") {
            id = "myrecipebook.android.library.jvmversion"
            implementationClass = "LibraryJvmVersionPlugin"
        }
        register("librarySdkPlugin") {
            id = "myrecipebook.android.library.sdk"
            implementationClass = "LibrarySdkPlugin"
        }
        register("libraryComposePlugin") {
            id = "myrecipebook.android.library.compose"
            implementationClass = "LibraryComposePlugin"
        }
        register("androidJvmVersionPlugin") {
            id = "myrecipebook.android.android.jvmversion"
            implementationClass = "AndroidJvmVersionPlugin"
        }
        register("androidSdkPlugin") {
            id = "myrecipebook.android.android.sdk"
            implementationClass = "AndroidSdkPlugin"
        }
        register("androidComposePlugin") {
            id = "myrecipebook.android.android.compose"
            implementationClass = "AndroidComposePlugin"
        }
    }
}
