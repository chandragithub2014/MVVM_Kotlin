package com.mvvm.databinding.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String) {
    if (url != "") {
        // Picasso.with(imageView.getContext()).load(url).resize(200, 200).into(imageView).
        Picasso.get().load(url).into(this)
    }
}
