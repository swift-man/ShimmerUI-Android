/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui

import androidx.compose.ui.graphics.Color

/**
 * Ready-to-use base and highlight color pairs.
 */
public enum class ShimmerConfigurationColorPreset(
    /** Placeholder or text base color. */
    public val baseColor: Color,
    /** Moving highlight color. */
    public val highlightColor: Color,
) {
    /** Neutral gray base with a bright white highlight. */
    Light(
        baseColor = Color.Gray.copy(alpha = 0.35f),
        highlightColor = Color.White.copy(alpha = 0.90f),
    ),

    /** White tones suitable for dark surfaces. */
    Dark(
        baseColor = Color.White.copy(alpha = 0.25f),
        highlightColor = Color.White.copy(alpha = 0.75f),
    ),

    /** Black tones suitable for light monochrome surfaces. */
    Black(
        baseColor = Color.Black.copy(alpha = 0.35f),
        highlightColor = Color.Black.copy(alpha = 0.75f),
    ),

    /** White tones suitable for dark monochrome surfaces. */
    White(
        baseColor = Color.White.copy(alpha = 0.35f),
        highlightColor = Color.White.copy(alpha = 0.90f),
    ),
}
