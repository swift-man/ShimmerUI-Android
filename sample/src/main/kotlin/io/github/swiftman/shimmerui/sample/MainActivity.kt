/*
 * Copyright 2026 Gorani
 * SPDX-License-Identifier: MIT
 */

package io.github.swiftman.shimmerui.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.swiftman.shimmerui.ShimmerLoading
import io.github.swiftman.shimmerui.ShimmerScreenPlaceholder
import io.github.swiftman.shimmerui.ShimmerText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    var isLoading by rememberSaveable { mutableStateOf(true) }

                    Column(
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                    ) {
                        ShimmerText("Thinking")

                        Button(onClick = { isLoading = !isLoading }) {
                            Text(if (isLoading) "Show content" else "Show shimmer")
                        }

                        ShimmerLoading(
                            isLoading = isLoading,
                            content = {
                                Text(
                                    text = "Content loaded",
                                    style = MaterialTheme.typography.headlineMedium,
                                )
                            },
                            placeholder = {
                                ShimmerScreenPlaceholder(rowCount = 3)
                            },
                        )
                    }
                }
            }
        }
    }
}
