package com.yanasanz.movies.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.yanasanz.movies.R

fun ImageView.load(url: String?, vararg transforms: BitmapTransformation = emptyArray()) {
    if (url == null) {
        setImageResource(R.drawable.ic_no_photo_24)
    } else {
        Glide.with(this)
            .load(url)
            .error(R.drawable.ic_broken_image_24)
            .timeout(10_000)
            .transform(*transforms)
            .into(this)
    }
}