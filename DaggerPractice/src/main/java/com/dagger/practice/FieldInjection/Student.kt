package com.dagger.practice.FieldInjection

class Student(studentDetails: StudentDetails) {
    var studentDetails = studentDetails

    fun studentInfo(){
       println("In Student.......")
    }
}