package com.dagger.practice.constructorDI

import com.dagger.practice.constructorDI.emp.EmployeeInfo
import dagger.Component

@Component
interface EmployeeInfoComponent {
    fun getEmpl(): EmployeeInfo
}