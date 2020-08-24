package com.example.bunqyapp.network.model

import com.google.gson.annotations.SerializedName

data class InstallationRequest(
    @SerializedName("client_public_key")
    var clientPublicKey: String = ""
)