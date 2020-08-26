package com.example.bunqyapp.network.model.monetary


import com.google.gson.annotations.SerializedName

data class Avatar(
    @SerializedName("anchor_uuid")
    val anchorUuid: String?,
    @SerializedName("image")
    val image: List<İmage>?,
    @SerializedName("uuid")
    val uuid: String?
)