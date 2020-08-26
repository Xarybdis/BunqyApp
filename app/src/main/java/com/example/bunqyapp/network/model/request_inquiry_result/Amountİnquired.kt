package com.example.bunqyapp.network.model.request_inquiry_result


import com.google.gson.annotations.SerializedName

data class Amountİnquired(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("value")
    val value: String?
)