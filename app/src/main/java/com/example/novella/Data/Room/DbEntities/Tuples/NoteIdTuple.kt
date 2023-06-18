package com.example.novella.Data.Room.DbEntities.Tuples

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class NoteIdTuple(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id") val id:Int = 0
)