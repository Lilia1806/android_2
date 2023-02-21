package com.example.android_2

import android.app.Application
import androidx.room.Room
import com.example.android_2.data.db.daos.AppDatabase
import com.example.android_2.utils.PreferenceHelper

class App : Application() {

    companion object {
        var appDatabase: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        getInstance()
        initPreference()
    }

    fun getInstance(): AppDatabase? {
        if (appDatabase == null) {
            appDatabase = applicationContext?.let {
                Room.databaseBuilder(
                    it,
                    AppDatabase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }

    private fun initPreference() {
        PreferenceHelper.unit(this)
    }
}