package com.example.bunqyapp.network.model

import com.google.gson.annotations.SerializedName

data class DeviceServerRequest(
    val description: String? = "",
    val secret: String? = "",
    @SerializedName("permitted_ips")
    val permittedIps: List<String?>
)