package com.dagger.practice.daggerapplication

import android.util.Log
import javax.inject.Inject

class Employee @Inject constructor(education: Education,college: College) {

    private val education : Education = education
    private val college:College = college


    fun work() {
        Log.d(TAG, "driving...")
    }

    //Method Injection ::::
    @Inject
    fun enableAccess(access: Access){
        access.setListener(this)
    }

    companion object {
        private const val TAG = "Employee"
    }

}