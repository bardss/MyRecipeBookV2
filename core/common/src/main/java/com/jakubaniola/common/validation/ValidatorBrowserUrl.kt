package com.jakubaniola.common.validation

import android.webkit.URLUtil

fun isBrowserUrlValid(url: String) = URLUtil.isValidUrl(url)

fun guessUrlWhenNotValid(url: String): String = if (isBrowserUrlValid(url)) {
    url
} else {
    URLUtil.guessUrl(url)
}
