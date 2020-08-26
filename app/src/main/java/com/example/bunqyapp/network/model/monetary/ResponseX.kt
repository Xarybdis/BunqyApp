package com.example.bunqyapp.network.model.monetary


import com.google.gson.annotations.SerializedName

data class ResponseX(
    @SerializedName("MonetaryAccountBank")
    val monetaryAccountBank: MonetaryAccountBank?
)