package com.example.bunqyapp.di

import com.example.bunqyapp.BuildConfig
import com.example.bunqyapp.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://public-api.sandbox.bunq.com/v1/"

val networkModule = module {
    single(named(NetworkModule.BASE_URL)) { BASE_URL }

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }

    /**
     * Creating client for in installation .
     */
    single(named(NetworkModule.INSTALLATION_CLIENT)) {
        val client = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            client.addInterceptor(get<HttpLoggingInterceptor>())
        }
        client.build()
    }
    /**
     * Creating client for in app requests.
     */
    single(named(NetworkModule.API_CLIENT)) {
        val client = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            client.addInterceptor(get<HttpLoggingInterceptor>())
        }
        client.build()
    }

    /**
     * We use moshi for to parse json. And we use it in Retrofit building.
     */
    single {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(NetworkModule.BASE_URL)))
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get(named(NetworkModule.INSTALLATION_CLIENT)))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(NetworkModule.BASE_URL)))
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get(named(NetworkModule.API_CLIENT)))
            .build()
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }
}


enum class NetworkModule {
    BASE_URL,
    INSTALLATION_CLIENT,
    API_CLIENT
}