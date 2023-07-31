package com.example.definexcase.api

import com.example.definexcase.api.model.listResponse.ListsResponse
import com.example.definexcase.api.model.LoginRequest
import com.example.definexcase.api.model.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ServiceInterface {
    @POST("login")
    fun postLogin(@Body loginRequest: LoginRequest): Single<LoginResponse>

    @GET("discoverFirstHorizontalList")
    fun getFirstList(@Header("token") token: String): Single<ListsResponse>

    @GET("discoverSecondHorizontalList")
    fun getSecondList(@Header("token") token: String): Single<ListsResponse>

    @GET("discoverThirthTwoColumnList")
    fun getThirdList(@Header("token") token: String): Single<ListsResponse>
}