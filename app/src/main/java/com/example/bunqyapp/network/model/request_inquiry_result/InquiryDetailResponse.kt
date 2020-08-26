package com.example.bunqyapp.network.model.request_inquiry_result


import com.google.gson.annotations.SerializedName

data class InquiryDetailResponse(
    @SerializedName("Response")
    val response: List<Response>?
)