package com.souzaemerson.muscleupgym.ui.extensions

import android.content.Context
import coil.decode.GifDecoder
import coil.request.ImageRequest

fun Any.setGif(url: String, context: Context) =
    ImageRequest.Builder(context)
        .data(url)
        .crossfade(true)
        .decoderFactory(GifDecoder.Factory())
        .build()