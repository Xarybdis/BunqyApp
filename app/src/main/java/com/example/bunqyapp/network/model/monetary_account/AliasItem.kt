package com.example.bunqyapp.network.model.monetary_account


import com.google.gson.annotations.SerializedName

data class AliasItem(
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("value")
    val value: String?
)