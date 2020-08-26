package com.example.bunqyapp.util

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.example.bunqyapp.R

class LoadingDialog(context: Context) : Dialog(context, android.R.style.Theme_Translucent_NoTitleBar) {
    companion object {
        fun init(context: Context): LoadingDialog {
            return LoadingDialog(context).apply {
                setCancelable(false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_loading)

    }
}