package com.mvvm.common.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager



class SharedPreferencesHelper {

    companion object{
        private const val  PREF_NAME = "emp_name"
        private var prefs:SharedPreferences ? = null
        @Volatile private var INSTANCE: SharedPreferencesHelper? = null
          private  val LOCK = Any()
       operator fun invoke(context: Context): SharedPreferencesHelper =
            INSTANCE ?: synchronized(LOCK) {
                INSTANCE ?: buildSharedPreferencesHelper(context).also {
                    INSTANCE = it
                }
            }

        private fun buildSharedPreferencesHelper(context: Context): SharedPreferencesHelper {
             prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferencesHelper()
        }

    }

    fun saveEmpName(name : String){
        prefs?.edit(commit = true){
           putString(PREF_NAME,name)
        }
    }
}