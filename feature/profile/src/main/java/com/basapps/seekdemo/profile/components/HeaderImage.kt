package com.basapps.seekdemo.profile.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.belalkhan.minitales.profile.R


@Composable
fun HeaderImage(modifier: Modifier,resource: Int = R.raw.alert) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resource))
    val progress by animateLottieCompositionAsState(composition = composition)

    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = modifier
    )
}