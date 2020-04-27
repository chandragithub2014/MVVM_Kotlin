package com.dagger.practice.interfaceInjection

import android.util.Log
import javax.inject.Inject

private const val TAG = "Doctor"
class Doctor @Inject constructor(doctorType: DoctorType,doctorAge: DoctorAge) {
var doctorType: DoctorType = doctorType
    var doctorAge:DoctorAge = doctorAge
    fun displayDoctorInfo(){
        doctorType.displayDoctorType()
        doctorAge.displayDoctorAge()
        Log.d(TAG,"In DisplayDoctorInfo()")
    }
}