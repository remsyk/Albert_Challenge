package com.example.alberttest.io.retrofit

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.alberttest.model.JSONData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



class ApiHandler {
    //creates retrofit factory
    val openLibraryService by lazy {
        RetrofitFactory.create()
    }

    lateinit var request: String

    private val bookList: MutableLiveData<List<JSONData?>> by lazy {
        MutableLiveData<List<JSONData?>>().also {
            search(request)
        }
    }

    @SuppressLint("CheckResult")
    fun search(searchRequest: String) {
        openLibraryService.search(searchRequest)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
        { response -> bookList.value = response.docs },
        { error -> Log.e("ApiHandler", "API request error", error) }
        )
    }

    fun getBookList(request: String): LiveData<List<JSONData?>> {
        this.request = request
        search(request)
        return bookList
    }

}


