package com.catapi.task.app

import androidx.multidex.MultiDexApplication

class CatAPIApplication : MultiDexApplication() {

    companion object {
        lateinit var mInstance: CatAPIApplication
    }

    override fun onCreate() {
        mInstance = this@CatAPIApplication
        super.onCreate()
    }

}