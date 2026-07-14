/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import io.github.swiftman.shimmerui.internal.LocalShimmerBaseColor
import io.github.swiftman.shimmerui.internal.consumeAllPointerInput

/**
 * Applies one synchronized shimmer sweep to an entire placeholder layout.
 *
 * Placeholder semantics and pointer interactions are disabled.
 *
 * @param modifier modifier applied to the placeholder container;
 * @param configuration shimmer appearance and animation values;
 * @param content placeholder layout.
 */
@Composable
public fun ShimmerContainer(
    modifier: Modifier = Modifier,
    configuration: ShimmerConfiguration = ShimmerConfiguration(),
    content: @Composable BoxScope.() -> Unit,
) {
    CompositionLocalProvider(
        LocalShimmerBaseColor provides configuration.baseColor,
    ) {
        Box(
            modifier = modifier
                .shimmer(configuration)
                .consumeAllPointerInput()
                .clearAndSetSemantics { },
            content = content,
        )
    }
}
