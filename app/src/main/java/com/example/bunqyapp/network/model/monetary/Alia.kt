package com.example.bunqyapp.network.model.monetary


import com.google.gson.annotations.SerializedName

data class Alia(
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("value")
    val value: String?
)