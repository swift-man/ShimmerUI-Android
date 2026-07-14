/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui.internal

import androidx.compose.runtime.staticCompositionLocalOf
import io.github.swiftman.shimmerui.ShimmerConfigurationColorPreset

internal val LocalShimmerBaseColor = staticCompositionLocalOf {
    ShimmerConfigurationColorPreset.Light.baseColor
}
