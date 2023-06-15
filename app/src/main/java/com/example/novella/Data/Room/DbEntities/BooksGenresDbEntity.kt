package com.example.novella.Data.Room.DbEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "BooksGenres",
    foreignKeys = arrayOf(
        ForeignKey(entity = BooksDbEntity::class,
    parentColumns = arrayOf("Id"),
    childColumns = arrayOf("BookId")
    ),
        ForeignKey(entity = GenresDbEntity::class,
        parentColumns = arrayOf("Id"),
        childColumns = arrayOf("GenreId")
        ))
)
data class BooksGenresDbEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id") val id: Int =0,
    @ColumnInfo(name = "BookId") val bookId: String,
    @ColumnInfo(name = "GenreId") val genreId: Int
) {
}