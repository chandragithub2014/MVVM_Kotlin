package com.rxretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvvm.appnavigator.replaceFragmentWithNoHistory
import com.rxretrofit.R

class RetroRxCoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retro_rx)
        replaceFragmentWithNoHistory(RetroRxCoroutineFragment(), R.id.container_fragment)
    }
}