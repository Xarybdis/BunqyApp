package com.example.bunqyapp.network.model.monetary


import com.google.gson.annotations.SerializedName

data class Setting(
    @SerializedName("color")
    val color: String?,
    @SerializedName("default_avatar_status")
    val defaultAvatarStatus: String?,
    @SerializedName("icon")
    val icon: Any?,
    @SerializedName("restriction_chat")
    val restrictionChat: String?
)