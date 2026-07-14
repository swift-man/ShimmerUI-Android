/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui.internal

import io.github.swiftman.shimmerui.ShimmerDirection
import kotlin.math.atan2

internal data class ShimmerUnitVector(
    val x: Float,
    val y: Float,
)

internal val ShimmerDirection.unitVector: ShimmerUnitVector
    get() = when (this) {
        ShimmerDirection.LeftToRight -> ShimmerUnitVector(x = 1f, y = 0f)
        ShimmerDirection.RightToLeft -> ShimmerUnitVector(x = -1f, y = 0f)
        ShimmerDirection.TopToBottom -> ShimmerUnitVector(x = 0f, y = 1f)
        ShimmerDirection.BottomToTop -> ShimmerUnitVector(x = 0f, y = -1f)
        ShimmerDirection.TopLeftToBottomRight -> ShimmerUnitVector(
            x = 0.70710678f,
            y = 0.70710678f,
        )
        ShimmerDirection.BottomRightToTopLeft -> ShimmerUnitVector(
            x = -0.70710678f,
            y = -0.70710678f,
        )
    }

internal val ShimmerDirection.angleDegrees: Float
    get() {
        val vector = unitVector
        return Math.toDegrees(
            atan2(vector.y, vector.x).toDouble(),
        ).toFloat()
    }
