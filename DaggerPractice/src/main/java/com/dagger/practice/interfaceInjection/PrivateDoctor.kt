package com.dagger.practice.interfaceInjection

import android.util.Log
import javax.inject.Inject

private const val TAG = "GovtDoctor"

class PrivateDoctor @Inject constructor(): DoctorType{

    override fun displayDoctorType() {
        Log.d(TAG,"This is Pvt Doctor")
    }

}