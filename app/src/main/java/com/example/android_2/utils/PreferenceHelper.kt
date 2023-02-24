package com.example.android_2.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private lateinit var sharedPreference: SharedPreferences

    fun unit(context: Context) {
        sharedPreference = context.getSharedPreferences("sitting", Context.MODE_PRIVATE)
    }

    var safeBoolean: Boolean
        set(value) = sharedPreference.edit().putBoolean("key", value).apply()
        get() = sharedPreference.getBoolean("key", false)


    var signUp:Boolean
        set(value) = sharedPreference.edit().putBoolean("sign",value).apply()
        get() = sharedPreference.getBoolean("sign",false)
}