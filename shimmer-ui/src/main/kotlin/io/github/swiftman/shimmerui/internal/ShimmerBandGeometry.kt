/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui.internal

import kotlin.math.hypot
import kotlin.math.max

internal data class ShimmerBandGeometry(
    val bandWidth: Float,
    val crossLength: Float,
) {
    constructor(
        width: Float,
        height: Float,
        bandWidthRatio: Float,
        minimumBandWidth: Float,
    ) : this(
        diagonal = normalizedDiagonal(width, height),
        bandWidthRatio = normalizedBandWidthRatio(bandWidthRatio),
        minimumBandWidth = normalizedMinimumBandWidth(minimumBandWidth),
    )

    private constructor(
        diagonal: Float,
        bandWidthRatio: Float,
        minimumBandWidth: Float,
    ) : this(
        bandWidth = max(
            minimumBandWidth,
            diagonal * BandWidthScaleFactor * bandWidthRatio,
        ),
        crossLength = diagonal * max(
            MinimumCrossLengthMultiplier,
            bandWidthRatio,
        ) + max(
            minimumBandWidth,
            diagonal * BandWidthScaleFactor * bandWidthRatio,
        ),
    )

    internal companion object {
        private const val MinimumDiagonal: Float = 1f
        private const val DefaultMinimumBandWidth: Float = 18f
        private const val BandWidthScaleFactor: Float = 0.18f
        private const val MinimumCrossLengthMultiplier: Float = 2.2f
        private const val DefaultBandWidthRatio: Float = 4f
        private const val MinimumBandWidthRatio: Float = 0.1f
        private const val MaximumBandWidthRatio: Float = 6f

        fun diagonal(width: Float, height: Float): Float =
            normalizedDiagonal(width, height)

        private fun normalizedDiagonal(width: Float, height: Float): Float {
            val value = hypot(width, height)
            return if (value.isFinite()) max(value, MinimumDiagonal) else MinimumDiagonal
        }

        private fun normalizedBandWidthRatio(value: Float): Float =
            if (value.isFinite()) {
                value.coerceIn(MinimumBandWidthRatio, MaximumBandWidthRatio)
            } else {
                DefaultBandWidthRatio
            }

        private fun normalizedMinimumBandWidth(value: Float): Float =
            if (value.isFinite()) {
                value.coerceAtLeast(0f)
            } else {
                DefaultMinimumBandWidth
            }
    }
}
