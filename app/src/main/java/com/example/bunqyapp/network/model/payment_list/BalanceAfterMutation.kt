package com.example.bunqyapp.network.model.payment_list


import com.google.gson.annotations.SerializedName

data class BalanceAfterMutation(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("value")
    val value: String?
)