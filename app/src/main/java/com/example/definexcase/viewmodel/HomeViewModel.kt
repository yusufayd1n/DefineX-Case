package com.example.definexcase.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.definexcase.api.CaseAPIService
import com.example.definexcase.api.model.ListResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val caseAPIService = CaseAPIService()
    private val disposable = CompositeDisposable()
    private val secondDisposable = CompositeDisposable()
    private val thirdDisposable = CompositeDisposable()

    val firstListLiveData = MutableLiveData<ListResponse>()
    val firstListError = MutableLiveData<Boolean>()
    val secondListLiveData = MutableLiveData<ListResponse>()
    val secondListError = MutableLiveData<Boolean>()
    val thirdListLiveData = MutableLiveData<ListResponse>()
    val thirdListError = MutableLiveData<Boolean>()

    fun getFirstList(token: String) {
        disposable.add(
            caseAPIService.getFirstList(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ListResponse>() {
                    override fun onSuccess(t: ListResponse) {
                        firstListLiveData.value = t
                    }

                    override fun onError(e: Throwable) {
                        firstListError.value = true
                        e.printStackTrace()
                    }

                })

        )
    }

    fun getSecondList(token: String) {
        secondDisposable.add(
            caseAPIService.getSecondList(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ListResponse>() {
                    override fun onSuccess(t: ListResponse) {
                        secondListLiveData.value = t
                    }

                    override fun onError(e: Throwable) {
                        secondListError.value = true
                        e.printStackTrace()
                    }

                })

        )
    }

    fun getThirdList(token: String) {
        thirdDisposable.add(
            caseAPIService.getThirdList(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ListResponse>() {
                    override fun onSuccess(t: ListResponse) {
                        thirdListLiveData.value = t
                    }

                    override fun onError(e: Throwable) {
                        thirdListError.value = true
                        e.printStackTrace()
                    }

                })

        )
    }
}