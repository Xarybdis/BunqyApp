package com.example.bunqyapp.network.model.request_inquiry_result


import com.google.gson.annotations.SerializedName

data class LabelUserX(
    @SerializedName("country")
    val country: String?,
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("public_nick_name")
    val publicNickName: String?,
    @SerializedName("uuid")
    val uuid: String?
)