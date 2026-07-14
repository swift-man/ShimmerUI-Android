/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Draws a vertical list of synchronized placeholder rows.
 */
@Composable
public fun ShimmerListPlaceholder(
    modifier: Modifier = Modifier,
    rowCount: Int = 5,
    rowSpacing: Dp = 20.dp,
) {
    val resolvedRowCount = rowCount.coerceAtLeast(1)
    val resolvedSpacing = if (rowSpacing.value.isFinite()) {
        rowSpacing.coerceAtLeast(0.dp)
    } else {
        20.dp
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(resolvedSpacing),
    ) {
        repeat(resolvedRowCount) { index ->
            ShimmerListRowPlaceholder(
                avatarSize = if (index == 0) 58.dp else 50.dp,
                titleWidth = if (index % 2 == 0) 132.dp else 104.dp,
                textLineCount = 2,
            )
        }
    }
}
