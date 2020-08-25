package com.example.bunqyapp.network.model.money_inquiry

import com.google.gson.annotations.SerializedName

data class InquiryResponse(
    @SerializedName("Error")
    val error: List<Error>?,
    @SerializedName("Response")
    val response: List<InquiryResult>? = null


)
