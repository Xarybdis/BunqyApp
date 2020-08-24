package com.example.bunqyapp.network.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class ServerPublicKey(
    @SerializedName( "server_public_key")
    val serverPublicKey: String = ""
)
