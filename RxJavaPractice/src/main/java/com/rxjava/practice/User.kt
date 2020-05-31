package com.rxjava.practice

data class User(val name : String, val gender:String, var email:String = "",var address: Address?=null)