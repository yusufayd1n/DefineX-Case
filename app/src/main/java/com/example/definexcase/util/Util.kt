package com.example.definexcase.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.definexcase.R

fun ImageView.downloadFromUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.realplaceholder)
        .error(R.drawable.error)
        .into(this)

}