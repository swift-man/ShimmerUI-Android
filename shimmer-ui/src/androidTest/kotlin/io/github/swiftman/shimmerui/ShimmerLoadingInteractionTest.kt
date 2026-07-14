/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShimmerLoadingInteractionTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadedContentReceivesPointerInputAsSoonAsLoadingEnds() {
        var clickCount = 0
        lateinit var finishLoading: () -> Unit

        composeTestRule.setContent {
            var isLoading by remember {
                mutableStateOf(true)
            }
            finishLoading = {
                isLoading = false
            }

            ShimmerLoading(
                isLoading = isLoading,
                modifier = Modifier.size(100.dp),
                transitionDurationMillis = 10_000,
                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .testTag(ContentTag)
                            .clickable {
                                clickCount += 1
                            },
                    )
                },
                placeholder = {
                    Box(modifier = Modifier.fillMaxSize())
                },
            )
        }

        composeTestRule.runOnIdle {
            finishLoading()
        }
        composeTestRule
            .onNodeWithTag(ContentTag)
            .performTouchInput {
                click()
            }

        composeTestRule.runOnIdle {
            assertEquals(1, clickCount)
        }
    }

    private companion object {
        const val ContentTag = "loaded-content"
    }
}
