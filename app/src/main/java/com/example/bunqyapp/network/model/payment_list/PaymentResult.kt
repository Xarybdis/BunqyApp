package com.example.bunqyapp.network.model.payment_list


import com.google.gson.annotations.SerializedName

data class PaymentResult(
    @SerializedName("Payment")
    val payment: Payment?
)