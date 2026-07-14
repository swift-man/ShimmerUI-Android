/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui

import androidx.compose.ui.graphics.Color
import io.github.swiftman.shimmerui.internal.ShimmerBandGeometry
import io.github.swiftman.shimmerui.internal.ShimmerBandGradientProfile
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ShimmerUITest {
    @Test
    fun configurationUsesAiLoadingDefaults() {
        val configuration = ShimmerConfiguration()

        assertEquals(1_600, configuration.durationMillis)
        assertEquals(4f, configuration.bandWidthRatio)
        assertEquals(ShimmerDirection.LeftToRight, configuration.direction)
        assertTrue(configuration.isActive)
    }

    @Test
    fun configurationNormalizesInvalidValues() {
        val nonFinite = ShimmerConfiguration(
            durationMillis = -1,
            bandWidthRatio = Float.POSITIVE_INFINITY,
        )
        val belowMinimum = ShimmerConfiguration(bandWidthRatio = -2f)
        val aboveMaximum = ShimmerConfiguration(bandWidthRatio = 10f)

        assertEquals(100, nonFinite.durationMillis)
        assertEquals(4f, nonFinite.bandWidthRatio)
        assertEquals(0.1f, belowMinimum.bandWidthRatio)
        assertEquals(6f, aboveMaximum.bandWidthRatio)
    }

    @Test
    fun activeReturnsNormalizedCopy() {
        val original = ShimmerConfiguration()
        val inactive = original.active(false)

        assertTrue(original.isActive)
        assertFalse(inactive.isActive)
        assertEquals(original.bandWidthRatio, inactive.bandWidthRatio)
    }

    @Test
    fun directionContainsSixAbsoluteCases() {
        assertEquals(6, ShimmerDirection.entries.size)
        assertEquals("Left → Right", ShimmerDirection.LeftToRight.title)
    }

    @Test
    fun gradientProfilePreservesHighlightAlpha() {
        val highlight = Color.White.copy(alpha = 0.75f)
        val colors = ShimmerBandGradientProfile.colorStops(highlight)

        assertEquals(7, colors.size)
        assertEquals(0f, colors.first().second.alpha)
        assertEquals(0.75f, colors[3].second.alpha)
        assertEquals(0f, colors.last().second.alpha)

        ShimmerBandGradientProfile.stops.forEachIndexed { index, stop ->
            assertEquals(
                highlight.alpha * stop.opacity,
                colors[index].second.alpha,
                0.0001f,
            )
        }
    }

    @Test
    fun geometryMatchesRepresentativeVisualSnapshots() {
        data class Snapshot(
            val width: Float,
            val height: Float,
            val bandWidth: Float,
            val crossLength: Float,
        )

        val snapshots = listOf(
            Snapshot(160f, 24f, 116.48879f, 763.64874f),
            Snapshot(320f, 180f, 264.34885f, 1_732.9536f),
            Snapshot(390f, 844f, 669.42035f, 4_388.4224f),
        )

        snapshots.forEach { snapshot ->
            val geometry = ShimmerBandGeometry(
                width = snapshot.width,
                height = snapshot.height,
                bandWidthRatio = 4f,
                minimumBandWidth = 18f,
            )

            assertEquals(snapshot.bandWidth, geometry.bandWidth, 0.001f)
            assertEquals(snapshot.crossLength, geometry.crossLength, 0.001f)
        }
    }

    @Test
    fun geometryPreservesCollapsedCoverage() {
        val geometry = ShimmerBandGeometry(
            width = 0f,
            height = 0f,
            bandWidthRatio = 4f,
            minimumBandWidth = 18f,
        )

        assertEquals(1f, ShimmerBandGeometry.diagonal(0f, 0f))
        assertEquals(18f, geometry.bandWidth)
        assertEquals(22f, geometry.crossLength)
    }
}
