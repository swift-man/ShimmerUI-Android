# ShimmerUI Android

![Badge](https://img.shields.io/badge/Kotlin-2.3.21-white.svg?style=flat-square&logo=Kotlin)
![Badge](https://img.shields.io/badge/Jetpack_Compose-4285F4.svg?style=flat-square&logo=JetpackCompose&logoColor=white)
![Badge - Version](https://img.shields.io/badge/Version-1.0.0-1177AA?style=flat-square)
![Badge - Gradle](https://img.shields.io/badge/Gradle-compatible-02303A?style=flat-square&logo=Gradle)
![Badge - Platform](https://img.shields.io/badge/platform-Android_23+-yellow?style=flat-square)
![Badge - License](https://img.shields.io/badge/license-MIT-black?style=flat-square)

Jetpack Compose shimmer components for Android.

## Requirements

- Android API 23+
- JDK 17
- Kotlin 2.3+
- Jetpack Compose

## Installation

The Maven coordinate reserved for the first release is:

```kotlin
dependencies {
    implementation("io.github.swiftman:shimmer-ui:1.0.0")
}
```

For local development, include the `shimmer-ui` module directly:

```kotlin
dependencies {
    implementation(project(":shimmer-ui"))
}
```

## Usage

```kotlin
ShimmerText("Thinking")

ShimmerContainer {
    ShimmerScreenPlaceholder(rowCount = 3)
}
```

Apply the effect to any composable:

```kotlin
Text(
    text = "Loading",
    color = configuration.baseColor,
    modifier = Modifier.shimmer(configuration)
)
```

Use `bandWidthRatio` to tune the sweep width. The default uses a calm 1.6-second sweep and a wide `4.0` band ratio suitable for AI loading states.

```kotlin
ShimmerText(
    text = "Thinking",
    configuration = ShimmerConfiguration(
        bandWidthRatio = 4.2f
    )
)
```

Keep real content and its placeholder in the same layout while loading:

```kotlin
ShimmerLoading(
    isLoading = isLoading,
    content = {
        LoadedContent()
    },
    placeholder = {
        ShimmerScreenPlaceholder(rowCount = 3)
    }
)
```

## Public API

- `ShimmerConfiguration`
- `ShimmerConfigurationColorPreset`
- `ShimmerDirection`
- `Modifier.shimmer()`
- `ShimmerText()`
- `ShimmerBlock()`
- `ShimmerContainer()`
- `ShimmerMultiline()`
- `ShimmerListRowPlaceholder()`
- `ShimmerListPlaceholder()`
- `ShimmerScreenPlaceholder()`
- `ShimmerLoading()`

## Documentation

Dokka documentation is published to:

- https://docs.gorani.me/ShimmerUI-Android/

Generate it locally with:

```sh
./gradlew :shimmer-ui:dokkaGeneratePublicationHtml
```

Deployment to `swift-man/docs` requires the `DOCS_DEPLOY_KEY` repository secret.

## Development

```sh
./gradlew :shimmer-ui:lintDebug :shimmer-ui:testDebugUnitTest :sample:assembleDebug
```

## License

ShimmerUI Android is available under the MIT License.
