package com.example.bunqyapp.di

import com.example.bunqyapp.ui.viewModel.GeneralViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * ViewModel DI
 */
val viewModelModule = module(override = true) {
    viewModel {
        GeneralViewModel()
    }
}