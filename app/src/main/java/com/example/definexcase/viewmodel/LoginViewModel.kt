package com.example.definexcase.viewmodel


import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.definexcase.api.CaseAPIService
import com.example.definexcase.api.model.LoginRequest
import com.example.definexcase.api.model.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ViewModel() {
    private val caseAPIService = CaseAPIService()
    private val disposable = CompositeDisposable()
    val loginLiveData = MutableLiveData<LoginResponse>()
    val loginError = MutableLiveData<Boolean>()

    fun postLogin(loginRequest: LoginRequest) {
        disposable.add(
            caseAPIService.login(loginRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<LoginResponse>() {
                    override fun onSuccess(t: LoginResponse) {
                        loginLiveData.value = t
                    }

                    override fun onError(e: Throwable) {
                        loginError.value = true
                        e.printStackTrace()
                    }

                })

        )
    }
}