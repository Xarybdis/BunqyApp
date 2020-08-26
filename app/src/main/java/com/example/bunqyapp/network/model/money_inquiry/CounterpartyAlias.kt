package com.example.bunqyapp.network.model.money_inquiry


import com.google.gson.annotations.SerializedName

data class CounterpartyAlias(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: String
)