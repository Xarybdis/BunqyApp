package com.example.bunqyapp.network.model.session_user_model


import com.google.gson.annotations.SerializedName

data class Avatar(
    @SerializedName("anchor_uuid")
    val anchorUuid: String,
    @SerializedName("image")
    val image: List<Image>,
    @SerializedName("uuid")
    val uuid: String
)