package com.example.gomrukkolkulatoru.common.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Double.format(digits: Int) = "%.${digits}f".format(this)

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}