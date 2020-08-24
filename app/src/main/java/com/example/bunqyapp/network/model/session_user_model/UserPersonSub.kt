package com.example.bunqyapp.network.model.session_user_model


import com.google.gson.annotations.SerializedName

data class UserPersonSub(
    @SerializedName("address_main")
    val addressMain: AddressMain,
    @SerializedName("address_postal")
    val addressPostal: AddressPostal,
    @SerializedName("alias")
    val alias: List<Alia>,
    @SerializedName("avatar")
    val avatar: Avatar,
    @SerializedName("billing_contract")
    val billingContract: List<BillingContract>,
    @SerializedName("country_of_birth")
    val countryOfBirth: Any,
    @SerializedName("created")
    val created: String,
    @SerializedName("customer")
    val customer: Customer,
    @SerializedName("customer_limit")
    val customerLimit: CustomerLimit,
    @SerializedName("daily_limit_without_confirmation_login")
    val dailyLimitWithoutConfirmationLogin: DailyLimitWithoutConfirmationLogin,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    @SerializedName("deny_reason")
    val denyReason: Any,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("document_status")
    val documentStatus: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_primary_document")
    val isPrimaryDocument: Boolean,
    @SerializedName("joint_membership")
    val jointMembership: Any,
    @SerializedName("language")
    val language: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("legal_name")
    val legalName: String,
    @SerializedName("middle_name")
    val middleName: String,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("notification_filters")
    val notificationFilters: List<NotificationFilter>,
    @SerializedName("pack_membership")
    val packMembership: Any,
    @SerializedName("place_of_birth")
    val placeOfBirth: String,
    @SerializedName("premium_trial")
    val premiumTrial: Any,
    @SerializedName("public_nick_name")
    val publicNickName: String,
    @SerializedName("public_uuid")
    val publicUuid: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("relations")
    val relations: List<Any>,
    @SerializedName("session_timeout")
    val sessionTimeout: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("sub_status")
    val subStatus: String,
    @SerializedName("tax_resident")
    val taxResident: Any,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("version_terms_of_service")
    val versionTermsOfService: String
)