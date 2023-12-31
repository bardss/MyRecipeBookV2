pluginManagement {
    includeBuild("plugins")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("di") {
            library("android", "com.google.dagger:hilt-android:2.44")
            library("compiler", "com.google.dagger:hilt-compiler:2.44")
            library("navigation", "androidx.hilt:hilt-navigation-compose:1.0.0")
            bundle("hilt", listOf("android", "navigation"))
            plugin("plugin", "com.google.dagger.hilt.android").version("2.44")
        }
        create("androidx") {
            library("core-ktx", "androidx.core:core-ktx:1.10.1")
            library("lifecycle-runtime-ktx", "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
            library("activity-compose", "androidx.activity:activity-compose:1.7.2")
            library(
                "lifecycle-runtime-compose",
                "androidx.lifecycle:lifecycle-runtime-compose:2.6.1"
            )
            library("compose-navigation", "androidx.navigation:navigation-compose:2.5.3")
            bundle(
                "compose",
                listOf(
                    "lifecycle-runtime-ktx",
                    "activity-compose",
                    "lifecycle-runtime-compose",
                    "compose-navigation"
                )
            )
            library("compose", "androidx.compose:compose-bom:2022.10.00")
        }
        create("testlibs") {
            library("junit-core", "junit:junit:4.13.2")
            library("junit-ext", "androidx.test.ext:junit:1.1.5")
            bundle(
                "junit",
                listOf("junit-core", "junit-ext")
            )
            library(
                "kotlinx-coroutines-core",
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1"
            )
            library(
                "kotlinx-coroutines-test",
                "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1"
            )
            bundle(
                "kotlinx",
                listOf(
                    "kotlinx-coroutines-core",
                    "kotlinx-coroutines-test",
                )
            )
            library("androidx-text-core-ktx", "androidx.test:core-ktx:1.5.0")
            library("androidx-text-ext-ktx", "androidx.test.ext:junit-ktx:1.1.5")
            library("androidx-text-runner", "androidx.test:runner:1.5.2")
            bundle(
                "androidx",
                listOf(
                    "androidx-text-core-ktx",
                    "androidx-text-ext-ktx"
                )
            )
            library("mockk", "io.mockk:mockk:1.13.5")
        }
        create("external") {
            library("kotlin-bom", "org.jetbrains.kotlin:kotlin-bom:1.8.0")
            library("coil", "io.coil-kt:coil-compose:2.4.0")
        }
        create("room") {
            library("runtime", "androidx.room:room-runtime:2.5.1")
            library("compiler", "androidx.room:room-compiler:2.5.1")
            library("ktx", "androidx.room:room-ktx:2.5.1")
        }
        create("kotlinx") {
            library("coroutines-android", "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
        }
    }
}
rootProject.name = "MyRecipeBook"
include(":app")
include(":designsystem")
include(":model")
include(":datalayer:repository")
include(":datalayer:datasource:database")
include(":feature:addeditrecipe")
include(":feature:recipedetails")
include(":feature:recipeslist")
include(":core:testing")
include(":core:common")
