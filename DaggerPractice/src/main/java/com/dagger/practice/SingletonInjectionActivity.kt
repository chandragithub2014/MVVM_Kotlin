package com.dagger.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dagger.practice.singletonInjection.DaggerStudentComponent
import com.dagger.practice.singletonInjection.Driver
import com.dagger.practice.singletonInjection.Student
import com.dagger.practice.singletonInjection.StudentModule
import javax.inject.Inject
import javax.inject.Named

class SingletonInjectionActivity : AppCompatActivity() {

    @Inject
    @field:Named("driver")
    lateinit var  driverName : String

    @Inject
    @field:Named("student Name")
    lateinit var studentName : String

    @Inject
    lateinit var driver: Driver

    @Inject
    lateinit var student: Student

    @Inject
    lateinit var student1: Student





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singleton_injection)

        var component = DaggerStudentComponent.builder()
            .studentModule(StudentModule("John","Pollock"))
            .build()

        component.inject(this)
        student.displayStudentInfo()
        student1.displayStudentInfo()
    }
}