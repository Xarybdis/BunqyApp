package com.example.bunqyapp.network.model.session_user_model


import com.google.gson.annotations.SerializedName

data class CustomerLimit(
    @SerializedName("limit_amount_monthly")
    val limitAmountMonthly: Any,
    @SerializedName("limit_card_credit_mastercard")
    val limitCardCreditMastercard: Int,
    @SerializedName("limit_card_debit_maestro")
    val limitCardDebitMaestro: Int,
    @SerializedName("limit_card_debit_maestro_virtual_subscription")
    val limitCardDebitMaestroVirtualSubscription: Int,
    @SerializedName("limit_card_debit_maestro_virtual_total")
    val limitCardDebitMaestroVirtualTotal: Int,
    @SerializedName("limit_card_debit_mastercard")
    val limitCardDebitMastercard: Int,
    @SerializedName("limit_card_debit_mastercard_virtual_subscription")
    val limitCardDebitMastercardVirtualSubscription: Int,
    @SerializedName("limit_card_debit_mastercard_virtual_total")
    val limitCardDebitMastercardVirtualTotal: Int,
    @SerializedName("limit_card_debit_wildcard")
    val limitCardDebitWildcard: Int,
    @SerializedName("limit_card_replacement")
    val limitCardReplacement: Int,
    @SerializedName("limit_card_wildcard")
    val limitCardWildcard: Int,
    @SerializedName("limit_monetary_account")
    val limitMonetaryAccount: Int,
    @SerializedName("limit_monetary_account_remaining")
    val limitMonetaryAccountRemaining: Int,
    @SerializedName("spent_amount_monthly")
    val spentAmountMonthly: Any
)