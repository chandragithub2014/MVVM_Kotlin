package com.mvvm.databinding.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvvm.appnavigator.replaceFragmentWithNoHistory
import com.mvvm.databinding.R

class BasicDataBindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_data_binding)
        replaceFragmentWithNoHistory(BasicDataBidingFragment(), R.id.container_fragment)
    }
}