/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Immutable values that control the shimmer appearance and animation.
 *
 * Invalid duration and band-width values are normalized to safe drawing values.
 */
@Immutable
public class ShimmerConfiguration(
    baseColor: Color = ShimmerConfigurationColorPreset.Light.baseColor,
    highlightColor: Color = ShimmerConfigurationColorPreset.Light.highlightColor,
    durationMillis: Int = DefaultDurationMillis,
    direction: ShimmerDirection = ShimmerDirection.LeftToRight,
    bandWidthRatio: Float = DefaultBandWidthRatio,
    isActive: Boolean = true,
) {
    /** Color rendered by the supplied shimmer placeholders. */
    public val baseColor: Color = baseColor

    /** Color swept over visible content pixels. */
    public val highlightColor: Color = highlightColor

    /** Duration of one complete sweep in milliseconds. */
    public val durationMillis: Int = durationMillis.coerceAtLeast(MinimumDurationMillis)

    /** Direction in which the highlight band moves. */
    public val direction: ShimmerDirection = direction

    /** Relative width of the highlight band, normalized to the 0.1 through 6.0 range. */
    public val bandWidthRatio: Float = normalizeBandWidthRatio(bandWidthRatio)

    /** Whether the shimmer animation is installed. */
    public val isActive: Boolean = isActive

    /** Returns a configuration with the requested active state. */
    public fun active(value: Boolean): ShimmerConfiguration = copy(isActive = value)

    /** Returns a normalized copy with the supplied replacement values. */
    public fun copy(
        baseColor: Color = this.baseColor,
        highlightColor: Color = this.highlightColor,
        durationMillis: Int = this.durationMillis,
        direction: ShimmerDirection = this.direction,
        bandWidthRatio: Float = this.bandWidthRatio,
        isActive: Boolean = this.isActive,
    ): ShimmerConfiguration = ShimmerConfiguration(
        baseColor = baseColor,
        highlightColor = highlightColor,
        durationMillis = durationMillis,
        direction = direction,
        bandWidthRatio = bandWidthRatio,
        isActive = isActive,
    )

    override fun equals(other: Any?): Boolean =
        other is ShimmerConfiguration &&
            baseColor == other.baseColor &&
            highlightColor == other.highlightColor &&
            durationMillis == other.durationMillis &&
            direction == other.direction &&
            bandWidthRatio == other.bandWidthRatio &&
            isActive == other.isActive

    override fun hashCode(): Int {
        var result = baseColor.hashCode()
        result = 31 * result + highlightColor.hashCode()
        result = 31 * result + durationMillis
        result = 31 * result + direction.hashCode()
        result = 31 * result + bandWidthRatio.hashCode()
        result = 31 * result + isActive.hashCode()
        return result
    }

    override fun toString(): String =
        "ShimmerConfiguration(" +
            "baseColor=" + baseColor +
            ", highlightColor=" + highlightColor +
            ", durationMillis=" + durationMillis +
            ", direction=" + direction +
            ", bandWidthRatio=" + bandWidthRatio +
            ", isActive=" + isActive +
            ")"

    private companion object {
        const val DefaultDurationMillis: Int = 1_600
        const val MinimumDurationMillis: Int = 100
        const val DefaultBandWidthRatio: Float = 4.0f
        const val MinimumBandWidthRatio: Float = 0.1f
        const val MaximumBandWidthRatio: Float = 6.0f

        fun normalizeBandWidthRatio(value: Float): Float =
            if (value.isFinite()) {
                value.coerceIn(MinimumBandWidthRatio, MaximumBandWidthRatio)
            } else {
                DefaultBandWidthRatio
            }
    }
}
