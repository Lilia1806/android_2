package com.example.android_2

import android.app.Application
import com.example.android_2.utils.PreferenceHelper

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initPreference()
    }

    private fun initPreference() {
        PreferenceHelper.unit(this)
    }
}