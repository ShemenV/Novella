package com.example.novella.Data.Room.DbEntities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.novella.Domain.Entities.Book

@Entity(
    tableName = "books"
)
data class BooksDbEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val author: String,
    val pageCount: Int,
    val description: String,
    val cover:String,
    val publisher:String
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