package com.example.definexcase.api

import com.example.definexcase.api.model.listResponse.ListsResponse
import com.example.definexcase.api.model.LoginRequest
import com.example.definexcase.api.model.LoginResponse
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CaseAPIService(private val api: ServiceInterface) {


    fun login(loginRequest: LoginRequest): Single<LoginResponse> {
        return api.postLogin(loginRequest)
    }

    fun getFirstList(token: String): Single<ListsResponse> {
        return api.getFirstList(token)
    }

    fun getSecondList(token: String): Single<ListsResponse> {
        return api.getSecondList(token)
    }

    fun getThirdList(token: String): Single<ListsResponse> {
        return api.getThirdList(token)
    }
}