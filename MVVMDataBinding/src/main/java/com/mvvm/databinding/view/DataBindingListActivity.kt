package com.mvvm.databinding.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvvm.appnavigator.replaceFragmentWithNoHistory
import com.mvvm.databinding.R

class DataBindingListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_binding_list)
        replaceFragmentWithNoHistory(DataBindingListFragment(), R.id.container_fragment)
    }
}