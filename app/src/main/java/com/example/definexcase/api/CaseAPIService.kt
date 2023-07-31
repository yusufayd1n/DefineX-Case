package com.example.definexcase.api

import com.example.definexcase.api.model.FirstListResponse
import com.example.definexcase.api.model.LoginRequest
import com.example.definexcase.api.model.LoginResponse
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CaseAPIService {
    private val BASE_URL = "https://teamdefinex-mobile-auth-casestudy.vercel.app/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ServiceInterface::class.java)

    fun login(loginRequest: LoginRequest): Single<LoginResponse> {
        return api.postLogin(loginRequest)
    }

    fun getFirstList(token: String): Single<FirstListResponse> {
        return api.getFirstList(token)
    }

    fun getSecondList(token: String): Single<FirstListResponse> {
        return api.getSecondList(token)
    }

    fun getThirdList(token: String): Single<FirstListResponse> {
        return api.getThirdList(token)
    }
}