package com.example.bunqyapp.network.model.session_user_model


import com.google.gson.annotations.SerializedName

data class BillingContractSubscription(
    @SerializedName("contract_date_end")
    val contractDateEnd: Any,
    @SerializedName("contract_date_start")
    val contractDateStart: String,
    @SerializedName("contract_version")
    val contractVersion: Int,
    @SerializedName("created")
    val created: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("sub_status")
    val subStatus: String,
    @SerializedName("subscription_type")
    val subscriptionType: String,
    @SerializedName("subscription_type_downgrade")
    val subscriptionTypeDowngrade: String,
    @SerializedName("updated")
    val updated: String
)