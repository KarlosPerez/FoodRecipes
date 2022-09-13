package com.karlosprojects.foodrecipes.presentation.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showStringSnackBar(
    messageRes: String,
    length: Int = Snackbar.LENGTH_LONG,
) {
    val snackBar = Snackbar.make(this, messageRes, length)
    snackBar.show()
}