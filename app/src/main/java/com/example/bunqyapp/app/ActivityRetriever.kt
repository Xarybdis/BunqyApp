package com.example.bunqyapp.app

import android.app.Activity
import android.content.Context

class ActivityRetriever(private val defaultCurrentActivityListener: DefaultCurrentActivityListener) {
    val context: Context = defaultCurrentActivityListener.context

    fun getActivity(): Activity? {
        return defaultCurrentActivityListener.currentActivity
    }
}