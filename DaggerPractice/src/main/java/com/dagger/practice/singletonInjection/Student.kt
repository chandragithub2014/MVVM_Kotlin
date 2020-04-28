package com.dagger.practice.singletonInjection

import android.util.Log

private const val TAG = "Student"
class Student constructor(driver:Driver,studentName:String){
    var studentName:String = studentName
    var driver:Driver = driver

     fun displayStudentInfo(){
         Log.d(TAG,"Student Info is :: Student  $studentName drives with $driver and driver Name is ${driver.driverName}")
     }
}