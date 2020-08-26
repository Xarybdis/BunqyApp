package com.example.bunqyapp.network.model

import com.google.gson.annotations.SerializedName

data class DeviceServerResponse(
    @SerializedName("Response")
    val response: List<DeviceServerResult>? = null,
    @SerializedName("Error")
    val error: List<Error>? = null
)
