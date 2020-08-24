package com.example.bunqyapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bunqyapp.network.ApiService
import com.example.bunqyapp.network.model.*
import com.example.bunqyapp.util.ConnectionSecurityUtils
import com.example.bunqyapp.util.StringUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.qualifier.named

class MainViewModel : ViewModel(), KoinComponent {
    val installationData = MutableLiveData<ServiceInstallationResponse>()
    val deviceServerData = MutableLiveData<DeviceServerResponse>()
    val sessionData = MutableLiveData<SessionServerResponse>()
    val loading = MutableLiveData<Boolean>()
    val onError = MutableLiveData<Boolean>()

    private val apiServiceAuth: ApiService = get((named("AUTH")))
    private val apiServiceClient: ApiService = get(named("API_CLIENT"))
    private val connectionSecurityUtils: ConnectionSecurityUtils = get()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getInstallation() {
        createInstallation()
    }

    fun getDeviceServer() {
        createDeviceServer()
    }

    fun startSession() {
        createSession()
    }

    private fun createInstallation() {
        loading.value = true
        //Bunu burda yapmak mantikli degil, bunu activity icinde yap ki destroy oldugunda yenilensin
        val installationRequestModel =
            InstallationRequest(connectionSecurityUtils.createPKCS8StandardPublicKey(connectionSecurityUtils.keyPair))

        disposable.add(
            apiServiceAuth.startInstallation(installationRequestModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ServiceInstallationResponse>() {
                    override fun onSuccess(data: ServiceInstallationResponse) {
                        installationData.value = data
                        loading.value = false
                        onError.value = false
                    }

                    override fun onError(e: Throwable) {
                        println(e.localizedMessage)
                        loading.value = false
                        onError.value = true
                    }
                })
        )
    }

    private fun createDeviceServer() {
        loading.value = true
        StringUtils.API_SIGNATURE = connectionSecurityUtils.unWrapThePublicKey(connectionSecurityUtils.keyPair)
        val deviceServerRequestModel = DeviceServerRequest("android-device", StringUtils.API_KEY, mutableListOf("*"))

        disposable.add(
            apiServiceClient.registerDevice(deviceServerRequestModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DeviceServerResponse>() {
                    override fun onSuccess(data: DeviceServerResponse) {
                        deviceServerData.value = data
                        loading.value = false
                        onError.value = false
                    }

                    override fun onError(e: Throwable) {
                        println(e.localizedMessage)
                        loading.value = false
                        onError.value = true
                    }
                })
        )
    }

    private fun createSession() {
        loading.value = true
        val sessionApiKey = SessionRequest(StringUtils.API_KEY)
        println("POint")
        disposable.add(
            apiServiceClient.startSession(sessionApiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SessionServerResponse>() {
                    override fun onSuccess(data: SessionServerResponse) {
                        sessionData.value = data
                        loading.value = false
                        onError.value = false
                    }

                    override fun onError(e: Throwable) {
                        println(e.localizedMessage)
                        loading.value = false
                        onError.value = true
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}