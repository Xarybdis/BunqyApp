package com.example.bunqyapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bunqyapp.network.ApiService
import com.example.bunqyapp.network.model.*
import com.example.bunqyapp.network.model.monetary.MonetaryResponse
import com.example.bunqyapp.network.model.money_inquiry.InquiryRequest
import com.example.bunqyapp.network.model.money_inquiry.InquiryResponse
import com.example.bunqyapp.network.model.payment_list.PaymentResponse
import com.example.bunqyapp.network.model.request_inquiry_result.InquiryDetailResponse
import com.example.bunqyapp.util.ConnectionSecurityUtils
import com.example.bunqyapp.util.StringUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.qualifier.named
import timber.log.Timber

class GeneralViewModel : ViewModel(), KoinComponent {
    val installationData = MutableLiveData<ServiceInstallationResponse>()
    val deviceServerData = MutableLiveData<DeviceServerResponse>()
    val sessionData = MutableLiveData<SessionServerResponse>()
    val inquiryData = MutableLiveData<InquiryResponse>()
    val monetaryAccountDetailData = MutableLiveData<MonetaryResponse>()
    val inquiryDetailData = MutableLiveData<InquiryDetailResponse>()
    val fetchPaymentData = MutableLiveData<PaymentResponse>()
    val loading = MutableLiveData<Boolean>()
    val onError = MutableLiveData<Boolean>()

    private val apiServiceAuth: ApiService = get((named("AUTH")))
    private val apiServiceClient: ApiService = get(named("API_CLIENT"))
    private val connectionSecurityUtils: ConnectionSecurityUtils = get()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getInstallation(installationRequest: InstallationRequest) {
        createInstallation(installationRequest)
    }

    fun getDeviceServer() {
        createDeviceServer()
    }

    fun startSession() {
        createSession()
    }

    fun getMonetaryAccountDetails(id: Int) {
        createMonetaryAccountDetails(id)
    }

    fun startInquiry(id: Int, monetaryId: Int, inquiryRequest: InquiryRequest) {
        createInquiry(id, monetaryId, inquiryRequest)
    }

    fun getInquiryDetails(userId: Int, monetaryId: Int, itemId: Int) {
        createInquiryDetails(userId, monetaryId, itemId)
    }

    fun getPaymentList(userId: Int, monetaryId: Int) {
        fetchPaymentList(userId, monetaryId)
    }

    private fun createInstallation(installationRequest: InstallationRequest) {
        loading.value = true

        disposable.add(
            apiServiceAuth.startInstallation(installationRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ServiceInstallationResponse>() {
                    override fun onSuccess(data: ServiceInstallationResponse) {
                        installationData.value = data
                        loading.value = false
                        onError.value = false
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e.localizedMessage)
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
                        Timber.e(e.localizedMessage)
                        loading.value = false
                        onError.value = true
                    }
                })
        )
    }

    private fun createSession() {
        loading.value = true
        val sessionApiKey = SessionRequest(StringUtils.API_KEY)
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
                        Timber.e(e.localizedMessage)
                        loading.value = false
                        onError.value = true
                    }
                })
        )
    }

    private fun createInquiry(id: Int, monetaryId: Int, inquiryRequest: InquiryRequest) {
        loading.value = true
        disposable.add(
            apiServiceClient.startNewPayment(id, monetaryId, inquiryRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<InquiryResponse>() {
                    override fun onSuccess(data: InquiryResponse) {
                        inquiryData.value = data
                        loading.value = false
                        onError.value = false
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e.localizedMessage)
                        loading.value = false
                        onError.value = true
                    }
                })

        )
    }

    private fun createMonetaryAccountDetails(id: Int) {
        loading.value = true
        disposable.add(
            apiServiceClient.getMonetaryAccountDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MonetaryResponse>() {
                    override fun onSuccess(data: MonetaryResponse) {
                        monetaryAccountDetailData.value = data
                        loading.value = false
                        onError.value = false
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e.localizedMessage)
                        loading.value = false
                        onError.value = true
                    }
                })
        )
    }

    private fun createInquiryDetails(userId: Int, monetaryId: Int, itemId: Int) {
        loading.value = true
        disposable.add(
            apiServiceClient.getInquiryDetails(userId, monetaryId, itemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<InquiryDetailResponse>() {
                    override fun onSuccess(data: InquiryDetailResponse) {
                        inquiryDetailData.value = data
                        loading.value = false
                        onError.value = false
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e.localizedMessage)
                        loading.value = false
                        onError.value = true
                    }
                })
        )
    }

    private fun fetchPaymentList(userId: Int, monetaryId: Int) {
        loading.value = true
        disposable.add(
            apiServiceClient.fetchPaymentList(userId, monetaryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PaymentResponse>() {
                    override fun onSuccess(data: PaymentResponse) {
                        fetchPaymentData.value = data
                        loading.value = false
                        onError.value = false
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e.localizedMessage)
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