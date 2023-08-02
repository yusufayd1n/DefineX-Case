package com.example.definexcase.di

import android.content.Context
import androidx.room.Room
import com.example.definexcase.api.CaseAPIService
import com.example.definexcase.api.ServiceInterface
import com.example.definexcase.db.ProductsDataBase
import com.example.definexcase.viewmodel.HomeViewModel
import com.example.definexcase.viewmodel.LoginViewModel
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { LoginViewModel(get()) }
}
val networkModule = module {
    single { CaseAPIService(get()) }
    single {
        val BASE_URL = "https://teamdefinex-mobile-auth-casestudy.vercel.app/"

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ServiceInterface::class.java)
    }
}

val databaseModule = module {
    single {
        val database = Room.databaseBuilder(
            get(), ProductsDataBase::class.java, "productsdatabase"
        ).build()
        database.productsDao()
    }

}