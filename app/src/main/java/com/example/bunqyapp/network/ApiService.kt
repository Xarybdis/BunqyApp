package com.example.bunqyapp.network

import com.example.bunqyapp.network.model.*
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("installation")
    fun startInstallation(@Body installationRequest: InstallationRequest): Single<ServiceInstallationResponse>

    @POST("device-server")
    fun registerDevice(@Body deviceServerRequest: DeviceServerRequest): Single<DeviceServerResponse>

    @POST("session-server")
    fun startSession(@Body sessionRequest: SessionRequest): Single<SessionServerResponse>


}