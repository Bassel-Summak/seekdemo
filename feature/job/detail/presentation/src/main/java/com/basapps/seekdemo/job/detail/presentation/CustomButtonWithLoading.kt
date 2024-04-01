@file:OptIn(ExperimentalAnimationApi::class)

package com.basapps.seekdemo.job.detail.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun CustomButtonWithLoading(
    text : String ="Click me",
    onClick: () -> Unit = {},
    modifier:Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.primary,
    isLoading:Boolean =false
){
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(backgroundColor)
            .clickable (enabled = !isLoading) {
                onClick()
            }
            .padding(start = 10.dp, end = 10.dp , top = 15.dp, bottom = 15.dp),
        contentAlignment = Alignment.Center
    ) {

        if (isLoading){
            LinearProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = textColor,
            )
        } else {
            Text(
                text = text,
                color = textColor,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}