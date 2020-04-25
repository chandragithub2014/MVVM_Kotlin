package com.room.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.room.db.userRepo.UserInfo
import com.room.db.userRepo.UserInfoDao

@Database(entities = [UserInfo::class], version = 1)
abstract class RepositoryDB : RoomDatabase() {

    abstract fun userDao(): UserInfoDao

    companion object {

        @Volatile private var INSTANCE: RepositoryDB? = null

        fun getInstance(context: Context): RepositoryDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                RepositoryDB::class.java, "Users.db")
                .build()
    }
}