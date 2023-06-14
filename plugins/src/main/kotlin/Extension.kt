import com.android.build.api.dsl.CommonExtension

internal fun configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = KOTLIN_COMPILER_EXTENSION_VERSION
        }
    }
}