package com.room.db.userRepo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface UserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userInfoList: MutableList<UserInfo>)

   /* @Query("DELETE FROM data_info")
    fun deleteAll()*/

    @Query("SELECT * from user_info ORDER BY id ASC")
     fun getAllPosts(): LiveData<MutableList<UserInfo>>

     @Query("SELECT * from user_info where username = :userName")
    suspend fun getUserInfo(userName:String):UserInfo

      @Query("DELETE from user_info where id = :id")
      suspend fun deleteUser(id:Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRow(userInfo: UserInfo): Long
}