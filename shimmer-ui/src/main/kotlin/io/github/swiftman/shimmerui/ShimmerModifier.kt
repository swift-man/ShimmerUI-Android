/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.layer.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp
import io.github.swiftman.shimmerui.internal.ShimmerBandGeometry
import io.github.swiftman.shimmerui.internal.ShimmerBandGradientProfile
import io.github.swiftman.shimmerui.internal.angleDegrees
import io.github.swiftman.shimmerui.internal.unitVector
import kotlin.math.abs

/**
 * Draws a moving highlight over the visible pixels of this composable.
 *
 * The effect uses offscreen source-atop blending so transparent areas remain untouched.
 */
@Composable
public fun Modifier.shimmer(
    configuration: ShimmerConfiguration = ShimmerConfiguration(),
): Modifier {
    if (!configuration.isActive) {
        return this
    }

    val transition = rememberInfiniteTransition(label = "Shimmer")
    val progress = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = configuration.durationMillis,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ),
        label = "Shimmer progress",
    )

    val vector = configuration.direction.unitVector
    val angleDegrees = configuration.direction.angleDegrees
    val gradientStops = ShimmerBandGradientProfile.colorStops(
        highlightColor = configuration.highlightColor,
    )

    return graphicsLayer {
        compositingStrategy = CompositingStrategy.Offscreen
    }.drawWithCache {
        val geometry = ShimmerBandGeometry(
            width = size.width,
            height = size.height,
            bandWidthRatio = configuration.bandWidthRatio,
            minimumBandWidth = 18.dp.toPx(),
        )
        val projectedHalfLength = (
            abs(vector.x) * size.width +
                abs(vector.y) * size.height
            ) / 2f
        val travelDistance = projectedHalfLength + geometry.bandWidth
        val brush = Brush.linearGradient(
            colorStops = gradientStops,
            start = Offset(-geometry.bandWidth / 2f, 0f),
            end = Offset(geometry.bandWidth / 2f, 0f),
        )

        onDrawWithContent {
            drawContent()

            val phase = progress.value.coerceIn(0f, 1f) * 2f - 1f
            val center = Offset(
                x = size.width / 2f + vector.x * phase * travelDistance,
                y = size.height / 2f + vector.y * phase * travelDistance,
            )

            withTransform({
                translate(left = center.x, top = center.y)
                rotate(degrees = angleDegrees, pivot = Offset.Zero)
            }) {
                drawRect(
                    brush = brush,
                    topLeft = Offset(
                        x = -geometry.bandWidth / 2f,
                        y = -geometry.crossLength / 2f,
                    ),
                    size = Size(
                        width = geometry.bandWidth,
                        height = geometry.crossLength,
                    ),
                    blendMode = BlendMode.SrcAtop,
                )
            }
        }
    }
}
