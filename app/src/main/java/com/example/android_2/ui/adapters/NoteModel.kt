package com.example.android_2.ui.adapters

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteModel(
    val title: String,
    val description: String,
    val data : String

) : java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
