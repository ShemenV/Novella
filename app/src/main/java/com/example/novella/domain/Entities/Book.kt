package com.example.novella.domain.Entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Book(
    var id: String? = null,
    var title: String? = null,
    var author: String? = null,
    var pageCount: Int ,
    var description: String? = null,
    var cover:ByteArray? = null,
    var publisher:String? = null,
    var readStatus: Int = 1,
    var coverUrl: String? = null,
    var coverString:String? = null,
    var startReadDate: LocalDate? = null,
    var finishReadDate: LocalDate? = null,
    var readedPages:Int
) : Parcelable{
var isSelect = false
}