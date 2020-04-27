package com.dagger.practice.constructorDI.emp

import android.util.Log
import javax.inject.Inject

private const val TAG = "EmpDetails"

class EmpDetails @Inject constructor() {

    fun displayEmpName(){
        Log.d(TAG,"In EmpDetails Constructor Injection")
    }

}