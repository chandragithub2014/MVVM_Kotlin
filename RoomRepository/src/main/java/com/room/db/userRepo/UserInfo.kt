package com.room.db.userRepo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class UserInfo(
                    val username:String,
                    @ColumnInfo(name = "password_hash")
                    val passwordHash:Int,
                    val age:Int,
                    val city:String,
                    val phone:String){
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}