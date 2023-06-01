package com.example.novella.domain.Entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: String? = null,
    val title: String?,
    val author: String? = null,
    val pageCount: Int? = null,
    val description: String? = null,
    val cover:ByteArray? = null,
    val publisher:String? = null,
    val readStatus: Int = 1,
    val coverUrl: String? = null
) : Parcelable