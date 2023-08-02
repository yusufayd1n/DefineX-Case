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

class HomeViewModel(application: Application, val caseAPIService: CaseAPIService) :
    BaseViewModel(application) {
    private val disposable = CompositeDisposable()

    val firstListLiveData = MutableLiveData<List<ListItems>>()
    val firstListError = MutableLiveData<Boolean>()
    val firstListLoading = MutableLiveData<Boolean>()

    val secondListLiveData = MutableLiveData<List<ListItems>>()
    val secondListError = MutableLiveData<Boolean>()
    val secondListLoading = MutableLiveData<Boolean>()

    val thirdListLiveData = MutableLiveData<List<ListItems>>()
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
                        storeListInSQLite(
                            t.list,
                            showList(
                                t.list,
                                firstListLiveData,
                                firstListError,
                                firstListLoading
                            ),
                            1
                        )
                    }

                    override fun onError(e: Throwable) {
                        getListFromSQLite(firstListLiveData, firstListError, firstListLoading, 1)
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
                .subscribeWith(object : DisposableSingleObserver<ListsResponse>() {
                    override fun onSuccess(t: ListsResponse) {
                        storeListInSQLite(
                            t.list,
                            showList(
                                t.list,
                                secondListLiveData,
                                secondListError,
                                secondListLoading
                            ),
                            2
                        )
                    }

                    override fun onError(e: Throwable) {
                        getListFromSQLite(secondListLiveData, secondListError, secondListLoading, 2)
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
                        storeListInSQLite(
                            t.list,
                            showList(
                                t.list,
                                thirdListLiveData,
                                thirdListError,
                                thirdListLoading
                            ),
                            3
                        )
                    }

                    override fun onError(e: Throwable) {
                        getListFromSQLite(thirdListLiveData, thirdListError, thirdListLoading, 3)
                        e.printStackTrace()
                    }

                })

        )
    }

    private fun showList(
        listItems: List<ListItems>,
        liveData: MutableLiveData<List<ListItems>>,
        listError: MutableLiveData<Boolean>,
        listLoading: MutableLiveData<Boolean>
    ) {
        liveData.value = listItems
        listError.value = false
        listLoading.value = false
    }

    private fun showListWhenError(
        listItems: List<ListItems>,
        liveData: MutableLiveData<List<ListItems>>,
        listError: MutableLiveData<Boolean>,
        listLoading: MutableLiveData<Boolean>
    ) {
        liveData.value = listItems
        listError.value = true
        listLoading.value = false
    }

    private fun storeListInSQLite(
        products: List<ListItems>,
        showFunction: Unit,
        listId: Int
    ) {
        launch {
            val dao = ProductsDataBase(getApplication()).productsDao()
            if (listId == 1) {
                dao.deleteAll()
            }
            products.forEach {
                it.listId = listId
            }
            val listLong = dao.insertAll(*products.toTypedArray())
            var i = 0
            while (i < products.size) {
                products[i].uuid = listLong[i].toInt()
                i++
            }
        }
    }

    private fun getListFromSQLite(
        liveData: MutableLiveData<List<ListItems>>,
        listError: MutableLiveData<Boolean>,
        listLoading: MutableLiveData<Boolean>,
        listId: Int
    ) {
        launch {
            val products =
                ProductsDataBase(getApplication()).productsDao().getAllProductsWithListId(listId)
            showListWhenError(products, liveData, listError, listLoading)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}