package com.example.bunqyapp.network.model

import com.google.gson.annotations.SerializedName

data class ServerPublicKey(
    @SerializedName( "server_public_key")
    val serverPublicKey: String = ""
)
