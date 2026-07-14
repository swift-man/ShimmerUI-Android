# AGENTS.md

## Scope

These instructions apply to the entire ShimmerUI Android repository.

## Architecture

ShimmerUI Android is a Jetpack Compose library. Keep the public API small, composable, and independent from Material libraries.

- Put public API in `io.github.swiftman.shimmerui`.
- Put implementation details in `io.github.swiftman.shimmerui.internal`.
- Prefer top-level composable functions and `Modifier` extensions.
- Use `internal` visibility for implementation boundaries. Folder names alone do not define API visibility.
- Preserve the shimmer geometry, gradient profile, timing, and direction behavior shared with the Swift implementation.
- Use the word "Shimmer", not "Skeleton", in public names and documentation.

## SOLID design

- Single responsibility: drawing, geometry, configuration, interaction blocking, and placeholder layout stay in separate files.
- Open/closed: expose Compose primitives such as `Modifier`, `Shape`, `Color`, and `TextStyle` so callers can customize behavior without changing internals.
- Liskov substitution: custom content supplied to containers must behave like ordinary Compose content when loading is disabled.
- Interface segregation: do not require Material 3 for the library module. Material belongs only in the sample app.
- Dependency inversion: public composables depend on immutable configuration and Compose abstractions, while platform drawing details remain internal.

## Public API

Public declarations require KDoc. New public API must include:

- a clear description;
- parameter documentation where behavior is not obvious;
- a usage example when the API has multiple content slots;
- tests for normalization or geometry changes.

Do not expose internal geometry, animation state, gradient stops, or pointer-input helpers.

## Formatting

- Follow Kotlin coding conventions with four-space indentation.
- Keep one public type or primary public composable per file.
- Use explicit imports and avoid wildcard imports.
- Add the repository SPDX header to Kotlin source files.
- Prefer immutable values and pure functions.
- Do not introduce non-finite drawing values.

## Testing

- Keep geometry and configuration tests as local JVM tests.
- Inject or isolate time-dependent behavior before adding animation snapshot tests.
- Add Compose UI tests for semantics and interaction changes.
- Do not weaken numeric snapshot tolerances without explaining the visual change.

## Documentation

- Generate HTML with Dokka v2.
- Document only public declarations in published output.
- Keep the module landing page in `docs/Module.md`.
- Do not commit generated Dokka output to this repository.
- The deployment workflow publishes generated HTML to `swift-man/docs/ShimmerUI-Android`.

## Dependencies

- The library module must not depend on Material or Material 3.
- Use the Compose BOM for Compose dependency alignment.
- Keep GitHub Actions pinned to full commit SHAs.
- Avoid adding dependencies when Compose or Kotlin standard APIs are sufficient.
