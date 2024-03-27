package com.souzaemerson.muscleupgym.ui.screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.souzaemerson.muscleupgym.R

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onFinishedAnimation: (state: Boolean) -> Unit
) {

    var visible by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.intro_muscleup))
        val animation = animateLottieCompositionAsState(composition = composition)

        LottieAnimation(composition = composition, progress = animation.progress)

        AnimatedVisibility(
            modifier = Modifier
                .offset(y = 120.dp)
                .align(Alignment.Center),
            visible = visible,
            enter = scaleIn(animationSpec = tween(700)),
            exit = scaleOut(animationSpec = tween(100))
        ) {
            Text(
                text = "Muscle Up",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraLight
            )
        }

        visible = when (animation.progress) {
            in Float.MIN_VALUE..0.3f -> false
            else -> true
        }

        if (animation.isAtEnd && animation.isPlaying) {
            onFinishedAnimation(true)
        }
    }
}