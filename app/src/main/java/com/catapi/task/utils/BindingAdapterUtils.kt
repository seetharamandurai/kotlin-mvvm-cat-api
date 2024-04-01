package com.catapi.task.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.catapi.task.R

@BindingAdapter("loadImage")
fun loadImage(thumbimg: ImageView, url: String) {
    Glide.with(thumbimg)
        .load(url)
        .placeholder(R.drawable.photo)
        .error(R.drawable.broken_image_24px)
        .fallback(R.drawable.photo)
        .into(thumbimg)


}

@BindingAdapter("imageUrl", "progressBar")
fun loadImageWithProgress(thumbimg: ImageView, url: String, progressBar: ProgressBar) {
    progressBar.visibility = View.VISIBLE
    Glide.with(thumbimg)
        .load(url)
        .placeholder(R.drawable.photo)
        .error(R.drawable.broken_image_24px)
        .fallback(R.drawable.photo)
        .addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.visibility = View.GONE
                return false
            }

        })
        .into(thumbimg)


}
