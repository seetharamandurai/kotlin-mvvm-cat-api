package com.catapi.task.repository

import com.catapi.task.api.ApiService
import com.catapi.task.network.RetrofitApiClient
import com.google.gson.JsonElement
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

class CatAPIRepository {

    companion object {
        var baseAppRepository = CatAPIRepository()
        var mCompositeDisposable = CompositeDisposable()
        var apiService: ApiService = RetrofitApiClient.getRetrofitApiClient()
            .create(ApiService::class.java)

        @Synchronized
        fun getInstance(mCompositeDisposable: CompositeDisposable): CatAPIRepository {
            Companion.mCompositeDisposable = mCompositeDisposable

            if (baseAppRepository == null)
                baseAppRepository = CatAPIRepository()

            return baseAppRepository
        }
    }

    fun getImageList(path: String, query: String): Observable<JsonElement> {
        return apiService.getImageList(path, query)
    }

}