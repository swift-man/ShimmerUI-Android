/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui

/**
 * Absolute screen direction used by the shimmer sweep.
 *
 * Left and right are not mirrored automatically in right-to-left layouts.
 */
public enum class ShimmerDirection(
    /** Human-readable direction title. */
    public val title: String,
) {
    /** Moves from the left edge to the right edge. */
    LeftToRight("Left → Right"),

    /** Moves from the right edge to the left edge. */
    RightToLeft("Right → Left"),

    /** Moves from the top edge to the bottom edge. */
    TopToBottom("Top → Bottom"),

    /** Moves from the bottom edge to the top edge. */
    BottomToTop("Bottom → Top"),

    /** Moves diagonally from top-left to bottom-right. */
    TopLeftToBottomRight("Top Left ↘ Bottom Right"),

    /** Moves diagonally from bottom-right to top-left. */
    BottomRightToTopLeft("Bottom Right ↖ Top Left"),
}
