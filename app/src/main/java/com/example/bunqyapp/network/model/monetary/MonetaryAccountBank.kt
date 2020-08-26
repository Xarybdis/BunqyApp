package com.example.bunqyapp.network.model.monetary


import com.google.gson.annotations.SerializedName

data class MonetaryAccountBank(
    @SerializedName("alias")
    val alias: List<Alia>?,
    @SerializedName("all_auto_save_id")
    val allAutoSaveİd: List<Any>?,
    @SerializedName("avatar")
    val avatar: Avatar?,
    @SerializedName("balance")
    val balance: Balance?,
    @SerializedName("connected_cards")
    val connectedCards: List<Any>?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("created")
    val created: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("daily_limit")
    val dailyLimit: DailyLimit?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("monetary_account_profile")
    val monetaryAccountProfile: MonetaryAccountProfile?,
    @SerializedName("overdraft_limit")
    val overdraftLimit: OverdraftLimit?,
    @SerializedName("public_uuid")
    val publicUuid: String?,
    @SerializedName("setting")
    val setting: Setting?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("sub_status")
    val subStatus: String?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("total_request_pending")
    val totalRequestPending: TotalRequestPending?,
    @SerializedName("updated")
    val updated: String?,
    @SerializedName("user_id")
    val userId: Int?
)