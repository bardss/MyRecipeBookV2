package com.jakubaniola.common

import android.app.SearchManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.navigateToUrl(url: String) {
    try {
        val intent = Intent(Intent.ACTION_SEARCH, Uri.parse(url))
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        val intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.putExtra(SearchManager.QUERY, url)
        startActivity(intent)
    }
}