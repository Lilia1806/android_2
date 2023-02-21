package com.example.android_2.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android_2.ui.adapters.NoteModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll() : LiveData<List<NoteModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteModel: NoteModel)

    @Delete
    fun delete(noteModel: NoteModel)

}