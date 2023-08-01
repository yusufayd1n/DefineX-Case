package com.example.definexcase.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.definexcase.api.CaseAPIService
import com.example.definexcase.api.model.listResponse.ListItems
import com.example.definexcase.api.model.listResponse.ListsResponse
import com.example.definexcase.db.ProductsDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {
    private val caseAPIService = CaseAPIService()
    private val disposable = CompositeDisposable()

    val firstListLiveData = MutableLiveData<List<ListItems>>()
    val firstListError = MutableLiveData<Boolean>()
    val firstListLoading = MutableLiveData<Boolean>()
    val secondListLiveData = MutableLiveData<ListsResponse>()
    val secondListError = MutableLiveData<Boolean>()
    val secondListLoading = MutableLiveData<Boolean>()
    val thirdListLiveData = MutableLiveData<ListsResponse>()
    val thirdListError = MutableLiveData<Boolean>()
    val thirdListLoading = MutableLiveData<Boolean>()

    fun getFirstList(token: String) {
        firstListLoading.value = true
        disposable.add(
            caseAPIService.getFirstList(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ListsResponse>() {
                    override fun onSuccess(t: ListsResponse) {
                        storeInSQLite(t.list)
                    }

                    override fun onError(e: Throwable) {
                        firstListLoading.value = false
                        firstListError.value = true
                        getDataFromSQLite()
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun showFirstList(listItems: List<ListItems>) {
        firstListLiveData.value = listItems
        firstListLoading.value = false
        firstListError.value = false
    }

    private fun storeInSQLite(products: List<ListItems>) {
        launch {
            val dao = ProductsDataBase(getApplication()).productsDao()
            dao.deleteAll()
            val listLong = dao.insertAll(*products.toTypedArray())
            var i = 0
            while (i < products.size) {
                products[i].uuid = listLong[i].toInt()
                i++
            }
            showFirstList(products)
        }
    }

    private fun getDataFromSQLite() {
        launch {
            val products = ProductsDataBase(getApplication()).productsDao().getAllProducts()
            showFirstList(products)
        }
    }


    fun getSecondList(token: String) {
        secondListLoading.value = true
        disposable.add(
            caseAPIService.getSecondList(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ListsResponse>() {
                    override fun onSuccess(t: ListsResponse) {
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
                .subscribeWith(object : DisposableSingleObserver<ListsResponse>() {
                    override fun onSuccess(t: ListsResponse) {
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