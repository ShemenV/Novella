package com.example.novella.Data.Room.DbEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.novella.domain.Entities.Book

@Entity(
    tableName = "books",
    foreignKeys = arrayOf(ForeignKey(entity = ReadStatusesDbEntity::class,
    parentColumns = arrayOf("Id"),
    childColumns = arrayOf("readStatus")))
)
data class BooksDbEntity(
    @PrimaryKey @ColumnInfo(name = "Id") val id: String,
    @ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name = "Author") val author: String?,
    @ColumnInfo(name = "PageCount") val pageCount: Int?,
    @ColumnInfo(name = "Description") val description: String?,
    @ColumnInfo(name = "Cover") val cover:ByteArray?,
    @ColumnInfo(name = "Publisher") val publisher:String?,
    @ColumnInfo(name = "readStatus", defaultValue = "1") val readStatus:Int,
    @ColumnInfo(name="CoverPath") val coverPath:String?
) {
    companion object{
        fun fromBook(book: Book) = BooksDbEntity(
            id = book.id!!,
            title = book.title!!,
            author = book.author,
            pageCount = book.pageCount,
            description = book.description,
            cover = book.cover,
            publisher = book.publisher,
            readStatus = book.readStatus,
            coverPath = book.coverString
        )
    }
    fun ToBook():Book = Book(
        id = id,
        title = title,
        author = author,
        pageCount = pageCount,
        description = description,
        cover = cover,
        publisher = publisher,
        readStatus = readStatus,
        coverString = coverPath
    )


}