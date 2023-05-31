package com.example.novella.domain.Entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: Long?,
    val title: String?,
    val author: String?,
    val pageCount: Int?,
    val description: String?,
    val cover:ByteArray?,
    val publisher:String?,
    val readStatus: Int,
    val coverUrl: String? = null
) : Parcelable