package com.example.bunqyapp.di

import com.example.bunqyapp.BuildConfig
import com.example.bunqyapp.network.ApiService
import com.example.bunqyapp.util.StringUtils
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://public-api.sandbox.bunq.com/v1/"

val networkModule = module(override = true) {

    single(named("BASE_URL")) { BASE_URL }

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }

    /**
     * Creating client for in installation .
     */
    single (named("AUTH")){
        val client = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            client.addInterceptor(get<HttpLoggingInterceptor>())
            client.addNetworkInterceptor(StethoInterceptor())
            client.addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("User-Agent", "Android")
                    .addHeader("X-Bunq-Language", "en_US")
                    .build()
                chain.proceed(newRequest)
            }
        }
        client.build()
    }

    /**
     * Creating client for in app requests.
     */
    single(named("API_CLIENT")) {
        val client = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            client.addInterceptor(get<HttpLoggingInterceptor>())
            client.addNetworkInterceptor(StethoInterceptor())
            client.addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("User-Agent", "Android")
                    .addHeader("X-Bunq-Language", "en_US")
                    .addHeader("X-Bunq-Client-Authentication", StringUtils.API_TOKEN)
                    .addHeader("X-Bunq-Client-Signature", StringUtils.API_SIGNATURE)
                    .addHeader("X-Bunq-Client-Request-Id", System.currentTimeMillis().toString())
                    .build()
                client.addNetworkInterceptor(StethoInterceptor())
                chain.proceed(newRequest)
            }
        }
        client.build()
    }

    single(named("AUTH")) {
        Retrofit.Builder()
            .baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get((named("AUTH"))))
            .build()
    }

    single (named("API_CLIENT")){
        Retrofit.Builder()
            .baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get(named("API_CLIENT")))
            .build()
    }

    single (named("AUTH")){
        get<Retrofit>((named("AUTH"))).create(ApiService::class.java)
    }

    single (named("API_CLIENT")){
        get<Retrofit>(named("API_CLIENT")).create(ApiService::class.java)
    }
}