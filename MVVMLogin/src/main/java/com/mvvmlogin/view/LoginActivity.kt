package com.mvvmlogin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvvm.appnavigator.replaceFragmentWithNoHistory
import com.mvvmlogin.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        replaceFragmentWithNoHistory(LoginFragment(), R.id.container_fragment)
    }
}