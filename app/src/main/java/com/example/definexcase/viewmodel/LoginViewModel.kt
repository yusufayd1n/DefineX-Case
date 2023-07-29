package com.example.definexcase.viewmodel


import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.definexcase.api.model.LoginRequest
import com.example.definexcase.api.model.LoginResponse

class LoginViewModel : ViewModel() {
    val loginLiveData = MutableLiveData<LoginResponse>()

    fun postLogin(loginRequest: LoginRequest) {
        loginLiveData.value = LoginResponse(true,"agsgafafg")
    }
}