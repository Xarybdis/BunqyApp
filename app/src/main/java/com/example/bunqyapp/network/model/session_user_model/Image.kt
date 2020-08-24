package com.example.bunqyapp.network.model.session_user_model


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("attachment_public_uuid")
    val attachmentPublicUuid: String,
    @SerializedName("content_type")
    val contentType: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("width")
    val width: Int
)