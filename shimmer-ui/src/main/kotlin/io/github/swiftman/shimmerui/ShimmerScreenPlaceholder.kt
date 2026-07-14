/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp

/**
 * Draws a complete example loading screen with header, media, text, and list rows.
 */
@Composable
public fun ShimmerScreenPlaceholder(
    modifier: Modifier = Modifier,
    rowCount: Int = 3,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(22.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ShimmerBlock(
                modifier = Modifier.size(44.dp),
                shape = CircleShape,
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ShimmerBlock(
                    modifier = Modifier
                        .width(124.dp)
                        .height(15.dp),
                    shape = RoundedCornerShape(7.5.dp),
                )

                ShimmerBlock(
                    modifier = Modifier
                        .width(82.dp)
                        .height(11.dp),
                    shape = RoundedCornerShape(5.5.dp),
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            ShimmerBlock(
                modifier = Modifier.size(38.dp),
                shape = RoundedCornerShape(10.dp),
            )
        }

        ShimmerBlock(
            modifier = Modifier
                .fillMaxWidth()
                .height(168.dp),
            shape = RoundedCornerShape(16.dp),
        )

        ShimmerMultiline(
            lineCount = 4,
            lineHeight = 14.dp,
            lineSpacing = 10.dp,
            cornerRadius = 7.dp,
            lastLineFillRatio = 0.62f,
        )

        ShimmerListPlaceholder(rowCount = rowCount.coerceAtLeast(1))
    }
}
