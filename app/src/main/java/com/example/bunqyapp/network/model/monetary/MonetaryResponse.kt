package com.example.bunqyapp.network.model.monetary


import com.google.gson.annotations.SerializedName

data class MonetaryResponse(
    @SerializedName("Pagination")
    val pagination: Pagination?,
    @SerializedName("Response")
    val response: List<ResponseX>?
)