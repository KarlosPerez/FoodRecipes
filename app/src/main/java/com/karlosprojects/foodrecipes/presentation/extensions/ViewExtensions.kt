package com.karlosprojects.foodrecipes.presentation.extensions

import android.content.Context
import android.view.View
import androidx.core.text.HtmlCompat
import com.google.android.material.snackbar.Snackbar

fun View.showStringSnackBar(
    messageRes: String,
    length: Int = Snackbar.LENGTH_LONG,
) {
    val snackBar = Snackbar.make(this, messageRes, length)
    snackBar.show()
}

fun Context.getHtmlString(string: String) =
    HtmlCompat.fromHtml(
        string,
        HtmlCompat.FROM_HTML_MODE_LEGACY
    )