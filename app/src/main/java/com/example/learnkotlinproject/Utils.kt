package com.example.learnkotlinproject

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import java.text.NumberFormat
import java.util.*

import java.util.*

fun formatLong(number: Long) : String{
    return NumberFormat.getInstance().format(number)
}

suspend fun loadSvg(imageView: ImageView, url: String) {
    if (url.lowercase(Locale.ENGLISH).endsWith("svg")) {
        val imageLoader = ImageLoader.Builder(imageView.context)
            .componentRegistry {
                add(SvgDecoder(imageView.context))
            }
            .build()
        val request = ImageRequest.Builder(imageView.context)
            .data(url)
            .target(imageView)
            .build()
        imageLoader.execute(request)
    } else {
        imageView.load(url)
    }
}