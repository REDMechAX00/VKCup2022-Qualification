package com.redmechax00.vkcup2022qualification

import android.app.Application
import androidx.annotation.AnimRes
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

class App : Application() {
    companion object {
        lateinit var instance: App private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    object Strings {
        fun get(@StringRes stringRes: Int): String {
            return instance.getString(stringRes)
        }
    }

    object Colors {
        fun get(@ColorRes colorRes: Int): Int {
            return instance.getColor(colorRes)
        }
    }
}

