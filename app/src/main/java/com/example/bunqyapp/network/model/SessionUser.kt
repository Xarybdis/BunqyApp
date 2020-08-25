package com.example.bunqyapp.network.model

import com.example.bunqyapp.network.model.session_user_model.AddressMain
import com.google.gson.annotations.SerializedName

data class SessionUser(
    val id: Int? = 0,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("address_main")
    val addressMain: AddressMain,
    @SerializedName("legal_name")
    val legalName: String,
    @SerializedName("gender")
    val gender: String
)
