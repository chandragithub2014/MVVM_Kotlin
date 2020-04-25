package com.mvvm.common.utils

import android.graphics.Color
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


fun initToolBar(toolbar: Toolbar, textView: TextView, title:String, activity:AppCompatActivity){

    toolbar.setBackgroundColor(Color.CYAN)
    textView.text = title
    activity?.setSupportActionBar(toolbar)
    activity?.let{
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activity.supportActionBar?.title = ""
    }

}