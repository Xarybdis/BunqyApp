package com.example.bunqyapp.network.model.session_user_model


import com.google.gson.annotations.SerializedName

data class NotificationFilter(
    @SerializedName("category")
    val category: String,
    @SerializedName("notification_delivery_method")
    val notificationDeliveryMethod: String
)