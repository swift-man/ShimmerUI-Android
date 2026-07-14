/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.clearAndSetSemantics
import io.github.swiftman.shimmerui.internal.LocalShimmerBaseColor
import io.github.swiftman.shimmerui.internal.consumeAllPointerInput

/**
 * Keeps real content and a placeholder in the same layout while cross-fading between them.
 *
 * Hidden content cannot receive pointer input or accessibility focus.
 *
 * @param isLoading whether the placeholder is visible;
 * @param modifier modifier applied to the shared layout;
 * @param configuration shimmer appearance and animation values;
 * @param transitionDurationMillis cross-fade duration in milliseconds;
 * @param content real loaded content;
 * @param placeholder loading placeholder content.
 */
@Composable
public fun ShimmerLoading(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    configuration: ShimmerConfiguration = ShimmerConfiguration(),
    transitionDurationMillis: Int = 280,
    content: @Composable BoxScope.() -> Unit,
    placeholder: @Composable BoxScope.() -> Unit,
) {
    val duration = transitionDurationMillis.coerceAtLeast(0)
    val animationSpec = tween<Float>(
        durationMillis = duration,
        easing = FastOutSlowInEasing,
    )
    val contentAlpha by animateFloatAsState(
        targetValue = if (isLoading) 0f else 1f,
        animationSpec = animationSpec,
        label = "Shimmer content alpha",
    )
    val contentScale by animateFloatAsState(
        targetValue = if (isLoading) 0.995f else 1f,
        animationSpec = animationSpec,
        label = "Shimmer content scale",
    )
    val placeholderAlpha by animateFloatAsState(
        targetValue = if (isLoading) 1f else 0f,
        animationSpec = animationSpec,
        label = "Shimmer placeholder alpha",
    )
    val placeholderScale by animateFloatAsState(
        targetValue = if (isLoading) 1f else 0.995f,
        animationSpec = animationSpec,
        label = "Shimmer placeholder scale",
    )

    Box(modifier = modifier) {
        val loadedContentModifier = Modifier.graphicsLayer {
            alpha = contentAlpha
            scaleX = contentScale
            scaleY = contentScale
        }.let { baseModifier ->
            if (isLoading) {
                baseModifier
                    .consumeAllPointerInput()
                    .clearAndSetSemantics { }
            } else {
                baseModifier
            }
        }

        Box(
            modifier = loadedContentModifier,
            content = content,
        )

        if (isLoading || placeholderAlpha > 0f) {
            CompositionLocalProvider(
                LocalShimmerBaseColor provides configuration.baseColor,
            ) {
                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            alpha = placeholderAlpha
                            scaleX = placeholderScale
                            scaleY = placeholderScale
                        }
                        .shimmer(
                            configuration.active(
                                configuration.isActive && isLoading,
                            )
                        )
                        .consumeAllPointerInput()
                        .clearAndSetSemantics { },
                    content = placeholder,
                )
            }
        }
    }
}
