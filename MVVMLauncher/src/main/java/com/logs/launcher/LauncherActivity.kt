package com.logs.launcher


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.logs.launcher.view.LauncherFragment
import com.mvvm.appnavigator.replaceFragmentWithNoHistory


class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        //Toast.makeText(this,"In onCreate() method of Activity", Toast.LENGTH_LONG).show()
       // supportFragmentManager.beginTransaction().replace(R.id.container_fragment,LauncherFragment()).commit()
        replaceFragmentWithNoHistory(LauncherFragment(), R.id.container_fragment)

    }


}