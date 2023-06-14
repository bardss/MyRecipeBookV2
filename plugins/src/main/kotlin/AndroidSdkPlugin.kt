import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidSdkPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<ApplicationExtension> {
                compileSdk = COMPILE_SDK

                defaultConfig {
                    minSdk = MIN_SDK
                }

                defaultConfig.targetSdk = TARGET_SDK
            }
        }
    }
}