package com.dagger.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dagger.practice.FieldInjection.DaggerStudentComponent
import com.dagger.practice.FieldInjection.Student
import javax.inject.Inject

class FieldInjectionActivity : AppCompatActivity() {
    @Inject
    lateinit var student: Student
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field_injection)

        var studentComponent = DaggerStudentComponent.builder().build()

        studentComponent.inject(this)
        student.studentInfo()
    }
}