package com.example.novella.Data.Room.DbEntities.Tuples

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class BookIdTuple(
    @PrimaryKey @ColumnInfo(name = "Id") val id: String
) {
}