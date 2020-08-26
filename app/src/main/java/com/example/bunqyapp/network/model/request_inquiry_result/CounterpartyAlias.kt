package com.example.bunqyapp.network.model.request_inquiry_result


import com.google.gson.annotations.SerializedName

data class CounterpartyAlias(
    @SerializedName("country")
    val country: String?,
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("iban")
    val iban: String?,
    @SerializedName("is_light")
    val isLight: Boolean?,
    @SerializedName("label_user")
    val labelUser: LabelUser?
)