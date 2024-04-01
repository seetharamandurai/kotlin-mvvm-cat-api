package com.catapi.task.network

import com.catapi.task.app.AppConstant.API_BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

class RetrofitApiClient {
    companion object {
        private var retrofit: Retrofit? = null

        fun getRetrofitApiClient(): Retrofit {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            retrofit = Retrofit
                .Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
            return retrofit as Retrofit
        }

    }

}