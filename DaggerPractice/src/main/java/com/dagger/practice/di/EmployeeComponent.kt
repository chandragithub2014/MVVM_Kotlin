package com.dagger.practice.di

import com.dagger.practice.MainActivity
import com.dagger.practice.daggerapplication.Employee
import dagger.Component

@Component
interface EmployeeComponent {
    fun getEmp() : Employee

    fun inject(mainActivity: MainActivity)
}