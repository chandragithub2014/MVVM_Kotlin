package com.dagger.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dagger.practice.daggerapplication.Employee
import com.dagger.practice.di.DaggerEmployeeComponent
import com.dagger.practice.di.EmployeeComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

   //For Constructor Injection
   // lateinit var employee : Employee

    //For Field Injection
    @Inject lateinit var employee : Employee

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var component : EmployeeComponent = DaggerEmployeeComponent.create()
       // employee = component.getEmp()  //Commented can be used during constructor injection
        component.inject(this)
        employee.work()
    }
}