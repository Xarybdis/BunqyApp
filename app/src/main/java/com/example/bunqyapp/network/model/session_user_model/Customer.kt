package com.example.bunqyapp.network.model.session_user_model


import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("billing_account_id")
    val billingAccountId: Int,
    @SerializedName("created")
    val created: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("invoice_notification_preference")
    val invoiceNotificationPreference: String,
    @SerializedName("updated")
    val updated: String
)