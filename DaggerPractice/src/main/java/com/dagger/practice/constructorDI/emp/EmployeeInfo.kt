package com.dagger.practice.constructorDI.emp

import javax.inject.Inject

class EmployeeInfo @Inject constructor(empDetails: EmpDetails){
var empDetails: EmpDetails = empDetails

    fun empDetailsInfo(){
       empDetails.displayEmpName()

    }
}