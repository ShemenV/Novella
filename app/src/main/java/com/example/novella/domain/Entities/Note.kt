package com.example.novella.domain.Entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.time.LocalDate

data class Note(
    val id:Int,
    val title:String,
    val text: String?,
    val book: Book? = null,
    val addDate: LocalDate
) {
}