package com.example.bunqyapp.network.model.session_user_model


import com.google.gson.annotations.SerializedName

data class BillingContract(
    @SerializedName("BillingContractSubscription")
    val billingContractSubscription: BillingContractSubscription
)