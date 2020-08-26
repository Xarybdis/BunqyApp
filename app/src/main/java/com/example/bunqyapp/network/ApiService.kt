package com.example.bunqyapp.network

import com.example.bunqyapp.network.model.*
import com.example.bunqyapp.network.model.monetary.MonetaryResponse
import com.example.bunqyapp.network.model.money_inquiry.InquiryRequest
import com.example.bunqyapp.network.model.money_inquiry.InquiryResponse
import com.example.bunqyapp.network.model.payment_list.PaymentResponse
import com.example.bunqyapp.network.model.request_inquiry_result.InquiryDetailResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("installation")
    fun startInstallation(@Body installationRequest: InstallationRequest): Single<ServiceInstallationResponse>

    @POST("device-server")
    fun registerDevice(@Body deviceServerRequest: DeviceServerRequest): Single<DeviceServerResponse>

    @POST("session-server")
    fun startSession(@Body sessionRequest: SessionRequest): Single<SessionServerResponse>

    @POST("user/{userId}/monetary-account/{monetaryAccountId}/request-inquiry")
    fun startNewPayment(
        @Path("userId") id: Int?,
        @Path("monetaryAccountId") monetaryAccountId: Int?,
        @Body newMoneyRequest: InquiryRequest
    ): Single<InquiryResponse>

    @GET("user/{userID}/monetary-account")
    fun getMonetaryAccountDetail(@Path("userID") id: Int?): Single<MonetaryResponse>

    @GET("user/{userID}/monetary-account/{monetaryAccountId}/request-inquiry/{itemId}")
    fun getInquiryDetails(
        @Path("userID") userId: Int?,
        @Path("monetaryAccountId") monetaryAccountId: Int?,
        @Path("itemId") itemId: Int?
    ): Single<InquiryDetailResponse>

    @GET("user/{userID}/monetary-account/{monetaryAccountId}/payment")
    fun fetchPaymentList(@Path("userID") id: Int?, @Path("monetaryAccountId") monetaryAccountId: Int?): Single<PaymentResponse>
}
