package com.example.bunqyapp.network.model.session_user_model


import com.google.gson.annotations.SerializedName

data class DailyLimitWithoutConfirmationLogin(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("value")
    val value: String
)