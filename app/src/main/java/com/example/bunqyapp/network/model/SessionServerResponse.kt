package com.example.bunqyapp.network.model

import com.google.gson.annotations.SerializedName

data class SessionServerResponse(
    @SerializedName("Response")
    val sessionServerResult: List<SessionServerResult>? = null,
    @SerializedName("Error")
    val error: List<Error>? = null
)
