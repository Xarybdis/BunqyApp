package com.example.bunqyapp.network.model.monetary


import com.google.gson.annotations.SerializedName

data class MonetaryAccountProfile(
    @SerializedName("profile_action_required")
    val profileActionRequired: String?,
    @SerializedName("profile_amount_required")
    val profileAmountRequired: ProfileAmountRequired?,
    @SerializedName("profile_drain")
    val profileDrain: Any?,
    @SerializedName("profile_fill")
    val profileFill: Any?
)