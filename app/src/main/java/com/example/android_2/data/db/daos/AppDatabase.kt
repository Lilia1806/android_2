package com.example.android_2.data.db.daos

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_2.ui.adapters.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun noteDao() : NoteDao

}