package com.example.bunqyapp.di

import com.example.bunqyapp.app.ActivityRetriever
import com.example.bunqyapp.app.DefaultCurrentActivityListener
import com.example.bunqyapp.util.ConnectionSecurityUtils
import org.koin.dsl.module

val applicationModule = module(override = true) {
    single { DefaultCurrentActivityListener() }
    single { ActivityRetriever(get()) }

    single { ConnectionSecurityUtils() }
}