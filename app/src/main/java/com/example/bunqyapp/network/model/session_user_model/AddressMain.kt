package com.example.bunqyapp.network.model.session_user_model


import com.google.gson.annotations.SerializedName

data class AddressMain(
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("extra")
    val extra: Any,
    @SerializedName("house_number")
    val houseNumber: String,
    @SerializedName("mailbox_name")
    val mailboxName: Any,
    @SerializedName("postal_code")
    val postalCode: String,
    @SerializedName("province")
    val province: Any,
    @SerializedName("street")
    val street: String
)