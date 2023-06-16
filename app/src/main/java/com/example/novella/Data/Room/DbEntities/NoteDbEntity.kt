package com.example.novella.Data.Room.DbEntities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.novella.domain.Entities.Note
import java.time.LocalDate

@Entity(
    tableName = "notes",
    foreignKeys = arrayOf(ForeignKey(entity = BooksDbEntity::class,
        parentColumns = arrayOf("Id"),
        childColumns = arrayOf("BookId")))
)
data class NoteDbEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id") val id:Int = 1,
    @ColumnInfo(name = "Title") val title:String,
    @ColumnInfo(name = "Text") val text: String?,
    @ColumnInfo(name="BookId") val bookId: String?,
    @ColumnInfo(name = "AddDate") val addDate: String
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun toNote() = Note(
        id = id,
        title = title,
        text = text,
        addDate = LocalDate.parse(addDate)
    )
}