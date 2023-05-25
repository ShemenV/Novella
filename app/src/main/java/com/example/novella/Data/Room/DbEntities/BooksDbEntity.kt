package com.example.novella.Data.Room.DbEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.novella.Domain.Entities.Book

@Entity(
    tableName = "books",
    foreignKeys = arrayOf(ForeignKey(entity = ReadStatusesDbEntity::class,
    parentColumns = arrayOf("Id"),
    childColumns = arrayOf("readStatus")))
)
data class BooksDbEntity(
    @PrimaryKey @ColumnInfo(name = "Id") val id: Long,
    @ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name = "Author") val author: String?,
    @ColumnInfo(name = "PageCount") val pageCount: Int,
    @ColumnInfo(name = "Description") val description: String?,
    @ColumnInfo(name = "Cover", typeAffinity = ColumnInfo.BLOB) val cover:ByteArray?,
    @ColumnInfo(name = "Publisher") val publisher:String?,
    @ColumnInfo(name = "readStatus", defaultValue = "1") val readStatus:Int
) {

    fun ToBook():Book = Book(
        id = id,
        title = title,
        author = author,
        pageCount = pageCount,
        description = description,
        cover = cover,
        publisher = publisher,
        readStatus = readStatus
    )
}