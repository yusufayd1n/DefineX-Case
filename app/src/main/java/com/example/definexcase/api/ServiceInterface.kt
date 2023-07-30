package com.example.definexcase.api

import com.example.definexcase.api.model.LoginRequest
import com.example.definexcase.api.model.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceInterface {
    @POST("https://teamdefinex-mobile-auth-casestudy.vercel.app/login")
    fun postLogin(@Body loginRequest: LoginRequest): Single<LoginResponse>
}