package com.example.bunqyapp.network.model

import com.google.gson.annotations.SerializedName

data class ServiceInstallationResponse(
    @SerializedName("Response")
    val response: List<InstallationResult>? = null,
    @SerializedName("Error")
    val error: List<Error>? = null
)