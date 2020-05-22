package com.mvvmcoroutine.retrofit.userlist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvvm.appnavigator.replaceFragmentWithNoHistory
import com.mvvmcoroutine.retrofit.R
import com.mvvmcoroutine.retrofit.login.view.RetrofitLoginFragment

class UserListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        replaceFragmentWithNoHistory(UserListFragment(), R.id.container_fragment)
    }
}