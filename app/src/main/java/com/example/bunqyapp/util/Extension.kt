package com.example.bunqyapp.util

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible

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