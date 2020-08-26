package com.example.bunqyapp.network.model.monetary


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("future_url")
    val futureUrl: Any?,
    @SerializedName("newer_url")
    val newerUrl: Any?,
    @SerializedName("older_url")
    val olderUrl: Any?
)