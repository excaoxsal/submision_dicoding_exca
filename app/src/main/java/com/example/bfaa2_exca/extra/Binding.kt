package com.example.bfaa2_exca.extra

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bfaa2_exca.R

@BindingAdapter("avatar")
fun avatar(imageView: ImageView,avatar:String) =
    Glide.with(imageView)
        .load(avatar)
        .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.ic_about_me))
        .into(imageView)