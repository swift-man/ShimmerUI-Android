/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui.internal

import androidx.compose.ui.graphics.Color

internal object ShimmerBandGradientProfile {
    internal data class Stop(
        val opacity: Float,
        val location: Float,
    )

    val stops: List<Stop> = listOf(
        Stop(opacity = 0f, location = 0f),
        Stop(opacity = 0.35f, location = 0.22f),
        Stop(opacity = 0.85f, location = 0.40f),
        Stop(opacity = 1f, location = 0.50f),
        Stop(opacity = 0.85f, location = 0.60f),
        Stop(opacity = 0.35f, location = 0.78f),
        Stop(opacity = 0f, location = 1f),
    )

    fun colorStops(highlightColor: Color): Array<Pair<Float, Color>> =
        stops.map { stop ->
            stop.location to highlightColor.copy(
                alpha = highlightColor.alpha * stop.opacity,
            )
        }.toTypedArray()
}
