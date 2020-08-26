package com.example.bunqyapp.network.model.request_inquiry_result


import com.google.gson.annotations.SerializedName

data class RequestInquiry(
    @SerializedName("address_billing")
    val addressBilling: Any?,
    @SerializedName("address_shipping")
    val addressShipping: Any?,
    @SerializedName("amount_responded")
    val amountResponded: AmountResponded?,
    @SerializedName("amount_inquired")
    val amountİnquired: Amountİnquired?,
    @SerializedName("attachment")
    val attachment: List<Any>?,
    @SerializedName("batch_id")
    val batchİd: Any?,
    @SerializedName("bunqme_share_url")
    val bunqmeShareUrl: Any?,
    @SerializedName("counterparty_alias")
    val counterpartyAlias: CounterpartyAlias?,
    @SerializedName("created")
    val created: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("geolocation")
    val geolocation: Any?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("merchant_reference")
    val merchantReference: Any?,
    @SerializedName("minimum_age")
    val minimumAge: Any?,
    @SerializedName("monetary_account_id")
    val monetaryAccountİd: Int?,
    @SerializedName("redirect_url")
    val redirectUrl: Any?,
    @SerializedName("reference_split_the_bill")
    val referenceSplitTheBill: Any?,
    @SerializedName("require_address")
    val requireAddress: Any?,
    @SerializedName("scheduled_id")
    val scheduledİd: Any?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("time_expiry")
    val timeExpiry: Any?,
    @SerializedName("time_responded")
    val timeResponded: String?,
    @SerializedName("updated")
    val updated: String?,
    @SerializedName("user_alias_created")
    val userAliasCreated: UserAliasCreated?,
    @SerializedName("user_alias_revoked")
    val userAliasRevoked: Any?
)