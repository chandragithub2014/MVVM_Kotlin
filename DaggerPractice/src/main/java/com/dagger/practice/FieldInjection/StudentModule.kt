package com.dagger.practice.FieldInjection

import dagger.Module
import dagger.Provides

@Module
class StudentModule {


    @Provides
    fun provideStudentDetails() : StudentDetails{
        var studentDetails = StudentDetails()
         studentDetails.studentInfo()
        return  studentDetails
    }


    @Provides
    fun  provideStudent(studentDetails: StudentDetails) : Student{
       return Student(studentDetails)
    }



}