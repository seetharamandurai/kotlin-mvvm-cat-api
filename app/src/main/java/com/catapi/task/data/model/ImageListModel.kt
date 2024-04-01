package com.catapi.task.data.model

import com.google.gson.annotations.SerializedName

data class ImageListModelItem(

    @SerializedName("width") var width: Int? = null,

    @SerializedName("id") var id: String? = null,

    @SerializedName("url") var url: String? = null,

    @SerializedName("height") var height: Int? = null
)