package com.example.android.topic41.presentation.view.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.android.topic41.R;
import com.squareup.picasso.Picasso;

public class ImageBindingAdapters {
    @BindingAdapter("picasso")
    public static void setPicassoImage(ImageView image, String resource) {
        Context context = image.getContext();

        Picasso.get()
                .load(resource)
                .placeholder(R.drawable.place_holder)
                .into(image);
    }

    @BindingAdapter("glide")
    public static void setGlideImage(ImageView image, String resource) {
        Context context = image.getContext();
        Glide.with(context)
                .load(resource)
                .placeholder(R.drawable.place_holder)
                .fitCenter()
                .into(image);
    }
}
