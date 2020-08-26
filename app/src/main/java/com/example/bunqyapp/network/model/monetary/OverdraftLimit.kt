package com.example.bunqyapp.network.model.monetary


import com.google.gson.annotations.SerializedName

data class OverdraftLimit(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("value")
    val value: String?
)