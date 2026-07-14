/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Draws multiple placeholder text lines with a shorter final line.
 */
@Composable
public fun ShimmerMultiline(
    modifier: Modifier = Modifier,
    lineCount: Int = 3,
    lineHeight: Dp = 15.dp,
    lineSpacing: Dp = 10.dp,
    cornerRadius: Dp = 6.dp,
    lastLineFillRatio: Float = 0.7f,
) {
    val resolvedLineCount = lineCount.coerceAtLeast(1)
    val resolvedLineHeight = lineHeight.normalized(minimum = 1.dp, fallback = 15.dp)
    val resolvedLineSpacing = lineSpacing.normalized(minimum = 0.dp, fallback = 10.dp)
    val resolvedCornerRadius = cornerRadius.normalized(minimum = 0.dp, fallback = 6.dp)
    val resolvedLastLineRatio = if (lastLineFillRatio.isFinite()) {
        lastLineFillRatio.coerceIn(0.05f, 1f)
    } else {
        0.7f
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(resolvedLineSpacing),
    ) {
        repeat(resolvedLineCount) { index ->
            val fillRatio = if (index == resolvedLineCount - 1) {
                resolvedLastLineRatio
            } else {
                1f
            }

            ShimmerBlock(
                modifier = Modifier
                    .fillMaxWidth(fillRatio)
                    .height(resolvedLineHeight),
                shape = RoundedCornerShape(resolvedCornerRadius),
            )
        }
    }
}

private fun Dp.normalized(minimum: Dp, fallback: Dp): Dp =
    if (value.isFinite()) coerceAtLeast(minimum) else fallback
