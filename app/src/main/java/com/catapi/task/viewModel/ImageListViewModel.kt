package com.catapi.task.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.catapi.task.app.CatAPIApplication
import com.catapi.task.repository.CatAPIRepository
import com.google.gson.JsonElement
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ImageListViewModel (application: Application = CatAPIApplication.mInstance
) : AndroidViewModel(application) {

    val mCompositeDisposable = CompositeDisposable()

    fun getImageList(path: String, query: String): Observable<JsonElement> {
        return CatAPIRepository.getInstance(mCompositeDisposable).getImageList(path, query)
    }

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }

}