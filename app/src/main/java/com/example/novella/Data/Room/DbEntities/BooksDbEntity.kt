package com.example.novella.Data.Room.DbEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.novella.Domain.Entities.Book

@Entity(
    tableName = "books"
)
data class BooksDbEntity(
    @PrimaryKey @ColumnInfo(name = "Id") val id: Long,
    @ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name = "Author") val author: String?,
    @ColumnInfo(name = "PageCount") val pageCount: Int,
    @ColumnInfo(name = "Description") val description: String?,
    @ColumnInfo(name = "Cover") val cover:String?,
    @ColumnInfo(name = "Publisher") val publisher:String?
) {

    fun ToBook():Book = Book(
        id = id,
        title = title,
        author = author,
        pageCount = pageCount,
        description = description,
        cover = cover,
        publisher = publisher
    )
}