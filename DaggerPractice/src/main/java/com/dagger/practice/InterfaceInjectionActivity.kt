package com.dagger.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dagger.practice.interfaceInjection.DaggerDoctorComponent
import com.dagger.practice.interfaceInjection.Doctor
import com.dagger.practice.interfaceInjection.DoctorType
import com.dagger.practice.interfaceInjection.PrivateDoctorModule
import javax.inject.Inject

class InterfaceInjectionActivity : AppCompatActivity() {
    @Inject
    lateinit var doctor: Doctor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interface_injection)
        var doctorComponent = DaggerDoctorComponent.builder().privateDoctorModule(
            PrivateDoctorModule(20)
        )
            .build()
        doctorComponent.inject(this)
        doctor.displayDoctorInfo()


    }
}