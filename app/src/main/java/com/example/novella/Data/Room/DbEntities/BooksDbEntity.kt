package com.example.novella.Data.Room.DbEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.novella.domain.Entities.Book
import java.time.LocalDate

@Entity(
    tableName = "Books",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = ReadStatusesDbEntity::class,
            parentColumns = arrayOf("Id"),
            childColumns = arrayOf("readStatus")
        )
    )
)
data class BooksDbEntity(
    @PrimaryKey @ColumnInfo(name = "Id") val id: String,
    @ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name = "Author") val author: String?,
    @ColumnInfo(name = "PageCount") val pageCount: Int?,
    @ColumnInfo(name = "Description") val description: String?,
    @ColumnInfo(name = "Cover") val cover: ByteArray?,
    @ColumnInfo(name = "Publisher") val publisher: String?,
    @ColumnInfo(name = "readStatus", defaultValue = "1") val readStatus: Int,
    @ColumnInfo(name = "CoverPath") val coverPath: String?,
    @ColumnInfo(name = "StartReadDate") val startReadDate: String? = null,
    @ColumnInfo(name = "FinishReadDate") val finishReadDate: String? = null,
    @ColumnInfo(name = "ReadedPage") val readedPages: Int = 0,
    @ColumnInfo(name = "IsDeleted") var isDeleted: Int? = 0
) {
    companion object {
        fun fromBook(book: Book) = BooksDbEntity(
            id = book.id!!,
            title = book.title!!,
            author = book.author,
            pageCount = book.pageCount,
            description = book.description,
            cover = book.cover,
            publisher = book.publisher,
            readStatus = book.readStatus,
            coverPath = book.coverString,
            startReadDate = book.startReadDate.toString(),
            finishReadDate = book.finishReadDate.toString(),
            readedPages = book.readedPages
        )
    }

    fun ToBook(): Book {
        var book = Book(
            id = id,
            title = title,
            author = author,
            description = description,
            cover = cover,
            publisher = publisher,
            readStatus = readStatus,
            coverString = coverPath,
            pageCount = pageCount!!,
            readedPages = readedPages
        )

        if (startReadDate != "null" && startReadDate != null) {
            book.startReadDate = LocalDate.parse(startReadDate)
        }
        if (finishReadDate != "null" && finishReadDate != null) {
            book.finishReadDate = LocalDate.parse(finishReadDate)
        }

        if (pageCount == null) {
            book.pageCount = 0
        }

        if (description == null) {
            book.description = ""
        }
        if (publisher == null) {
            book.publisher = "Неизвестно"
        }
        if (author == null) {
            book.author = "Неизвестно"
        }
        if (readedPages == null) {
            book.readedPages = 0
        }

        return book
    }


}