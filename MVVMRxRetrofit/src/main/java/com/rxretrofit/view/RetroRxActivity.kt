package com.rxretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvvm.appnavigator.replaceFragmentWithNoHistory
import com.rxretrofit.R

class RetroRxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retro_rx)
        replaceFragmentWithNoHistory(RetroRXFragment(), R.id.container_fragment)
    }
}