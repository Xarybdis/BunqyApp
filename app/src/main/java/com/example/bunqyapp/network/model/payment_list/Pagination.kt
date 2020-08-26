package com.example.bunqyapp.network.model.payment_list


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("future_url")
    val futureUrl: String?,
    @SerializedName("newer_url")
    val newerUrl: Any?,
    @SerializedName("older_url")
    val olderUrl: String?
)