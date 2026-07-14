import java.net.URI
import org.jetbrains.dokka.gradle.engine.parameters.VisibilityModifier

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.dokka)
}

group = "io.github.swiftman"
version = providers.gradleProperty("VERSION_NAME").get()

android {
    namespace = "io.github.swiftman.shimmerui"
    compileSdk = 36

    defaultConfig {
        minSdk = 23
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    api(platform(libs.compose.bom))
    api(libs.compose.foundation)
    api(libs.compose.ui)

    implementation(libs.compose.animation)

    testImplementation(libs.junit)
}

dokka {
    dokkaPublications.html {
        moduleName.set("ShimmerUI Android")
        moduleVersion.set(project.version.toString())
        outputDirectory.set(layout.buildDirectory.dir("dokka/html"))
        includes.from(rootProject.file("docs/Module.md"))
        failOnWarning.set(true)
    }

    dokkaSourceSets.named("main") {
        documentedVisibilities.set(setOf(VisibilityModifier.Public))
        reportUndocumented.set(true)
        skipEmptyPackages.set(true)
        suppressGeneratedFiles.set(true)

        sourceLink {
            localDirectory.set(file("src/main/kotlin"))
            remoteUrl.set(
                URI(
                    "https://github.com/swift-man/ShimmerUI-Android/tree/main/" +
                        "shimmer-ui/src/main/kotlin"
                )
            )
            remoteLineSuffix.set("#L")
        }
    }
}
