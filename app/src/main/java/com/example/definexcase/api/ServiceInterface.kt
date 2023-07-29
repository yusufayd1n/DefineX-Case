package com.example.definexcase.api

import com.example.definexcase.api.model.LoginRequest
import com.example.definexcase.api.model.LoginResponse
import retrofit2.http.POST

interface ServiceInterface {
    @POST("https://teamdefinex-mobile-auth-casestudy.vercel.app/login")
    suspend fun postLogin(loginRequest: LoginRequest): LoginResponse
}