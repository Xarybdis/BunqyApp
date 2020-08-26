package com.example.bunqyapp.network.model.payment_list


import com.google.gson.annotations.SerializedName

data class PaymentResponse(
    @SerializedName("Pagination")
    val pagination: Pagination?,
    @SerializedName("Response")
    val paymentResult: List<PaymentResult>?
)