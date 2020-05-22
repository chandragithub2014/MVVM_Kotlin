package com.mvvmcoroutine.retrofit.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvvm.appnavigator.replaceFragmentWithNoHistory
import com.mvvmcoroutine.retrofit.R

class RetrofitLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_login)
        replaceFragmentWithNoHistory(RetrofitLoginFragment(), R.id.container_fragment)
    }
}