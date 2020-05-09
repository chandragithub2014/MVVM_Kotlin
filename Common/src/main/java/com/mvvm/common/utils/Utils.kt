package com.mvvm.common.utils

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mvvm.common.R


fun initToolBar(toolbar: Toolbar, textView: TextView, title:String, activity:AppCompatActivity){

    toolbar.setBackgroundColor(Color.CYAN)
    textView.text = title
    activity?.setSupportActionBar(toolbar)
    activity?.let{
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activity.supportActionBar?.title = ""
    }

}

fun getCircularDrawable(context:Context):CircularProgressDrawable{

    return  CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri:String?, progressDrawable: CircularProgressDrawable){
    var options:RequestOptions = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_default_icon)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)

}