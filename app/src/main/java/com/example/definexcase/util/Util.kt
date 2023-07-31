package com.example.definexcase.util

import android.content.Context
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
fun loadData(context: Context, key: String): String? {
    val sharedPreferences =
        context.getSharedPreferences(key, Context.MODE_PRIVATE)
    return sharedPreferences.getString(key, "")
}