package com.example.bunqyapp.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import java.text.SimpleDateFormat

fun visible(isVisible: Boolean, vararg views: View) {
    for (view in views) {
        view.isVisible = isVisible
    }
}

fun replaceSource(sourceImage: Int, vararg imageViews: ImageView) {
    for (view in imageViews) {
        view.setImageResource(sourceImage)
    }
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}
