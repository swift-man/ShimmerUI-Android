/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import io.github.swiftman.shimmerui.internal.LocalShimmerBaseColor

/**
 * Draws the base shape used to compose shimmer placeholder layouts.
 *
 * @param modifier size and layout modifiers for the block;
 * @param shape outline used to draw the block.
 */
@Composable
public fun ShimmerBlock(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
) {
    Box(
        modifier = modifier.background(
            color = LocalShimmerBaseColor.current,
            shape = shape,
        ),
    )
}
