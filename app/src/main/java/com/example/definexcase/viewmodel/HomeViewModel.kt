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

    val firstListLiveData = MutableLiveData<ListResponse>()
    val firstListError = MutableLiveData<Boolean>()
    val firstListLoading = MutableLiveData<Boolean>()
    val secondListLiveData = MutableLiveData<ListResponse>()
    val secondListError = MutableLiveData<Boolean>()
    val secondListLoading = MutableLiveData<Boolean>()
    val thirdListLiveData = MutableLiveData<ListResponse>()
    val thirdListError = MutableLiveData<Boolean>()
    val thirdListLoading = MutableLiveData<Boolean>()

    fun getFirstList(token: String) {
        firstListLoading.value = true
        disposable.add(
            caseAPIService.getFirstList(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ListResponse>() {
                    override fun onSuccess(t: ListResponse) {
                        firstListLiveData.value = t
                        firstListLoading.value = false
                        firstListError.value = false
                    }

                    override fun onError(e: Throwable) {
                        firstListLoading.value = false
                        firstListError.value = true
                        e.printStackTrace()
                    }

                })

        )
    }

    fun getSecondList(token: String) {
        secondListLoading.value = true
        disposable.add(
            caseAPIService.getSecondList(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ListResponse>() {
                    override fun onSuccess(t: ListResponse) {
                        secondListLiveData.value = t
                        secondListLoading.value = false
                        secondListError.value = false
                    }

                    override fun onError(e: Throwable) {
                        secondListLoading.value = false
                        secondListError.value = true
                        e.printStackTrace()
                    }

                })

        )
    }

    fun getThirdList(token: String) {
        thirdListLoading.value = true
        disposable.add(
            caseAPIService.getThirdList(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ListResponse>() {
                    override fun onSuccess(t: ListResponse) {
                        thirdListLiveData.value = t
                        thirdListLoading.value = false
                        thirdListError.value = false
                    }

                    override fun onError(e: Throwable) {
                        thirdListLoading.value = false
                        thirdListError.value = true
                        e.printStackTrace()
                    }

                })

        )
    }
}