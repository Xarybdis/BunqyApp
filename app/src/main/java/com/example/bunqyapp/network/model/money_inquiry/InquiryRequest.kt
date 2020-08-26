package com.example.bunqyapp.network.model.money_inquiry


import com.google.gson.annotations.SerializedName

data class InquiryRequest(
    @SerializedName("allow_bunqme")
    var allowBunqme: Boolean,
    @SerializedName("amount_inquired")
    var amountInquired: AmountInquired,
    @SerializedName("counterparty_alias")
    var counterpartyAlias: CounterpartyAlias,
    @SerializedName("description")
    var description: String
)