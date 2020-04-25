package com.mvvm.appnavigator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransactionWithHistory { replace(frameId, fragment) }
}
fun AppCompatActivity.replaceFragmentWithNoHistory(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransactionWithoutHistory { replace(frameId, fragment) }
}

inline fun FragmentManager.inTransactionWithHistory(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().addToBackStack("Added Fragment").commit()
}
inline fun FragmentManager.inTransactionWithoutHistory(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}
fun FragmentActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransactionWithHistory { replace(frameId, fragment) }
}

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    var intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}