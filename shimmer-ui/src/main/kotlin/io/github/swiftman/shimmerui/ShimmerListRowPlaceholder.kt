/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Draws one placeholder row with a circular image and text lines.
 */
@Composable
public fun ShimmerListRowPlaceholder(
    modifier: Modifier = Modifier,
    avatarSize: Dp = 52.dp,
    titleWidth: Dp = 118.dp,
    textLineCount: Int = 2,
) {
    val resolvedAvatarSize = avatarSize.positiveOr(52.dp)
    val resolvedTitleWidth = titleWidth.positiveOr(118.dp)

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.Top,
    ) {
        ShimmerBlock(
            modifier = Modifier.size(resolvedAvatarSize),
            shape = CircleShape,
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            ShimmerBlock(
                modifier = Modifier
                    .width(resolvedTitleWidth)
                    .height(14.dp),
                shape = RoundedCornerShape(7.dp),
            )

            ShimmerMultiline(
                lineCount = textLineCount.coerceAtLeast(1),
                lineHeight = 11.dp,
                lineSpacing = 8.dp,
                cornerRadius = 5.5.dp,
                lastLineFillRatio = 0.58f,
            )
        }
    }
}

private fun Dp.positiveOr(fallback: Dp): Dp =
    if (value.isFinite()) coerceAtLeast(1.dp) else fallback
