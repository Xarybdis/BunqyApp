package com.example.bunqyapp.network.model.payment_list


import com.google.gson.annotations.SerializedName

data class LabelUser(
    @SerializedName("country")
    val country: String?,
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("public_nick_name")
    val publicNickName: String?,
    @SerializedName("uuid")
    val uuid: String?
)