package com.example.novella.domain.Entities

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Note  (
    var id:Int? = null,
    var title:String = "",
    var text: String? = "",
    var book: Book? = null,
    val addDate: LocalDate = LocalDate.now()
): Parcelable {
}