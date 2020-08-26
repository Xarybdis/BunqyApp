package com.example.bunqyapp.network.model.payment_list


import com.google.gson.annotations.SerializedName

data class Payment(
    @SerializedName("address_billing")
    val addressBilling: Any?,
    @SerializedName("address_shipping")
    val addressShipping: Any?,
    @SerializedName("alias")
    val alias: Alias?,
    @SerializedName("amount")
    val amount: Amount?,
    @SerializedName("attachment")
    val attachment: List<Any>?,
    @SerializedName("balance_after_mutation")
    val balanceAfterMutation: BalanceAfterMutation?,
    @SerializedName("batch_id")
    val batchİd: Any?,
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
    @SerializedName("monetary_account_id")
    val monetaryAccountİd: Int?,
    @SerializedName("payment_auto_allocate_instance")
    val paymentAutoAllocateİnstance: Any?,
    @SerializedName("request_reference_split_the_bill")
    val requestReferenceSplitTheBill: List<Any>?,
    @SerializedName("scheduled_id")
    val scheduledİd: Any?,
    @SerializedName("sub_type")
    val subType: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updated")
    val updated: String?
)