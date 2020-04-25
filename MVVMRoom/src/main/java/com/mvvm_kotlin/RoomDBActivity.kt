package com.mvvm_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mvvm.appnavigator.replaceFragmentWithNoHistory
import com.mvvm_kotlin.view.RoomDataBaseFragment

class RoomDBActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,"In MVVM Room Activity",Toast.LENGTH_LONG).show()
        replaceFragmentWithNoHistory(RoomDataBaseFragment(), R.id.container_fragment)

    }
}