package com.jakubaniola.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

fun String.grantPersistentUriPermission(context: Context) {
    try {
        if (isNotEmpty()) {
            val uri = Uri.parse(this)
            uri.grantPersistentUriPermission(context)
        }
    } catch (e: Exception) {
        Log.e("MyRecipeBook", "GrantPersistentUriPermission\n$e")
    }
}

fun Uri.grantPersistentUriPermission(context: Context) {
    context.contentResolver.takePersistableUriPermission(
        this,
        Intent.FLAG_GRANT_READ_URI_PERMISSION
    )
}