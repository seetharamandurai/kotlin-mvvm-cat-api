package com.catapi.task.api

import com.google.gson.JsonElement
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("{search}")
    fun getImageList(
        @Path("search") search: String,
        @Query("limit") limit: String
    ): Observable<JsonElement>

}