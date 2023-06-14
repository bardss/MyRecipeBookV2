import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LibrarySdkPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                compileSdk = COMPILE_SDK

                defaultConfig {
                    minSdk = MIN_SDK
                }

                defaultConfig.targetSdk = TARGET_SDK
            }
        }
    }
}