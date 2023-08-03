package com.example.definexcase.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.definexcase.R

fun ImageView.downloadFromUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.error)
        .into(this)
}

fun loadData(context: Context, key: String): String? {
    val sharedPreferences =
        context.getSharedPreferences(key, Context.MODE_PRIVATE)
    return sharedPreferences.getString(key, "")
}

fun saveData(key: String, data: String, context: Context) {
    val sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(key, data)
    editor.apply()
}

fun checkForInternet(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false

        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    } else {
        @Suppress("DEPRECATION") val networkInfo =
            connectivityManager.activeNetworkInfo ?: return false
        @Suppress("DEPRECATION")
        return networkInfo.isConnected
    }
}