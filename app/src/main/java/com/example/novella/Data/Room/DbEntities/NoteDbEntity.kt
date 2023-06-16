package com.example.novella.Data.Room.DbEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
    @ColumnInfo(name="BookId") val bookId: String?
) {
}