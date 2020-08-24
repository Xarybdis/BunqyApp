package com.example.bunqyapp.network.model

import com.google.gson.annotations.SerializedName

data class InstallationResult(
    @SerializedName("Id")
    val id: Id?,

    @SerializedName("Token")
    val token: Token?,

    @SerializedName("ServerPublicKey")
    val serverPublicKey: ServerPublicKey?
) {
    companion object {
        const val ID = 0
        const val TOKEN = 1
        const val SERVER_KEY = 2
    }
}