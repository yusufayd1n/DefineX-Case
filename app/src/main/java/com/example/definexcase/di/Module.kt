package com.example.definexcase.di

import com.example.definexcase.api.CaseAPIService
import com.example.definexcase.viewmodel.HomeViewModel
import com.example.definexcase.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { CaseAPIService() }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { LoginViewModel(get()) }
}