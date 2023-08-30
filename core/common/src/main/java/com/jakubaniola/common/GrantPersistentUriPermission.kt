package com.jakubaniola.common

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Uri.grantPersistentUriPermission(context: Context) {
    context.contentResolver.takePersistableUriPermission(
        this,
        Intent.FLAG_GRANT_READ_URI_PERMISSION
    )
}
