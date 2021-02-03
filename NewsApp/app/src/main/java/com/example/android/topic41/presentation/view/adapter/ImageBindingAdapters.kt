package com.example.android.topic41.presentation.view.adapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.android.topic41.R
import com.squareup.picasso.Picasso

@BindingAdapter("picasso")
fun setPicassoImage(image: ImageView, resource: String) {
    val context = image.context
    Picasso.get()
            .load(if (resource.isEmpty()) null else resource)
            .placeholder(R.drawable.place_holder)
            .into(image)
}

@BindingAdapter("glide")
fun setGlideImage(image: ImageView, resource: String) {
    val context = image.context
    Glide.with(context)
            .load(resource)
            .placeholder(R.drawable.place_holder)
            .fitCenter()
            .into(image)
}

