package com.redmechax00.vkcup2022qualification.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.redmechax00.vkcup2022qualification.R

fun startFragment(context: AppCompatActivity, fragment: Fragment, addToStack: Boolean) {
    val fragmentTransaction = context.supportFragmentManager.beginTransaction()
    if (addToStack) {
        fragmentTransaction.addToBackStack(null)
    }

    fragmentTransaction
        .replace(R.id.data_container, fragment)
        .commit()
}