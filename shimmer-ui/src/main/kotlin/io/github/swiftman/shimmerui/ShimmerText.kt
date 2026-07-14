/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Displays text filled with the configured base color and shimmer sweep.
 *
 * @param text text to display;
 * @param modifier modifier applied to the text;
 * @param style text style before the shimmer base color is applied;
 * @param configuration shimmer appearance and animation values.
 */
@Composable
public fun ShimmerText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    configuration: ShimmerConfiguration = ShimmerConfiguration(),
) {
    BasicText(
        text = text,
        modifier = modifier.shimmer(configuration),
        style = style.copy(color = configuration.baseColor),
    )
}
