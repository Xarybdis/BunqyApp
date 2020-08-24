package com.example.bunqyapp.network.model

import com.google.gson.annotations.SerializedName

data class SessionServerResult(
    @SerializedName("Id")
    val sessionId: Id?,
    @SerializedName("Token")
    val sessionToken: Token?,
    @SerializedName("UserPerson")
    val sessionUser: SessionUser
)
