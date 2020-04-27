package com.dagger.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dagger.practice.constructorDI.DaggerEmployeeInfoComponent

class ConstructorInjectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constructor_injection)
       var component = DaggerEmployeeInfoComponent.create()

       var empInfo =  component.getEmpl()
        empInfo.empDetailsInfo()
    }
}