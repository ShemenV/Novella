package com.example.novella.domain.Entities

import android.os.Parcelable
import com.example.novella.Data.Room.DbEntities.BooksDbEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: String? = null,
    var title: String?,
    var author: String? = null,
    var pageCount: Int? = null,
    var description: String? = null,
    var cover:ByteArray? = null,
    var publisher:String? = null,
    var readStatus: Int = 1,
    var coverUrl: String? = null,
) : Parcelable{
var isSelect = false
}