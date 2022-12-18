package com.redmechax00.vkcup2022qualification.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.redmechax00.vkcup2022qualification.R

fun startFragment(
    context: AppCompatActivity,
    fragment: Fragment,
    data: ArrayList<String>?,
    addToStack: Boolean
) {
    val fragmentTransaction = context.supportFragmentManager.beginTransaction()
    if (addToStack) {
        fragmentTransaction.addToBackStack(null)
    }

    val bundle = Bundle()
    bundle.putStringArrayList(KEY_FAVORITES_ARRAY_LIST, data)
    fragment.arguments = bundle

    fragmentTransaction
        .replace(R.id.data_container, fragment)
        .commit()
}