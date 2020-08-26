package com.example.bunqyapp.network.model.monetary_account

import com.google.gson.annotations.SerializedName

data class MonetaryAccountDetailResponse(
    val id: Int? = 0,
    val created: String? = "",
    val updated: String? = "",
    @SerializedName("display_name")
    val displayName: String? = "",
    @SerializedName("user_id")
    val userId: Int? = 0,
    val alias: Alias? = null
)
