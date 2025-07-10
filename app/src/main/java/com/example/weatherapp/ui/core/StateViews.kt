package com.example.weatherapp.ui.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.weatherapp.R

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val preLoaderLottieComposition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(
                R.raw.loading,
            )
        )

        val preLoaderProgress by animateLottieCompositionAsState(
            preLoaderLottieComposition,
            iterations = LottieConstants.IterateForever,
            isPlaying = true,
        )

        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center,
        ) {
            LottieAnimation(
                composition = preLoaderLottieComposition,
                progress = { preLoaderProgress },
                modifier = Modifier.size(150.dp)
            )
        }
    }
}

@Composable
fun ErrorView(modifier: Modifier = Modifier, message: String) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val preLoaderLottieComposition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(
                R.raw.no_data_found
            )
        )

        val preLoaderProgress by animateLottieCompositionAsState(
            preLoaderLottieComposition,
            iterations = LottieConstants.IterateForever,
            isPlaying = true,
        )

        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieAnimation(
                    composition = preLoaderLottieComposition,
                    progress = { preLoaderProgress },
                    modifier = Modifier.size(100.dp),
                )
                Text(
                    message,
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Light),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun IdleView(modifier: Modifier = Modifier) {

    val preLoaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.weather_partly_cloudy
        )
    )

    val preLoaderProgress by animateLottieCompositionAsState(
        preLoaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = preLoaderLottieComposition,
                progress = { preLoaderProgress },
                modifier = Modifier.size(200.dp)
            )
            Text(
                stringResource(R.string.search_for_a_city_to_see_its_weather_details),
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Light),
                textAlign = TextAlign.Center,
            )
        }
    }
}