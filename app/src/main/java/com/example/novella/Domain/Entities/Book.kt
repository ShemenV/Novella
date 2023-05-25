package com.example.novella.Domain.Entities

data class Book(
    val id: Long?,
    val title: String?,
    val author: String?,
    val pageCount: Int?,
    val description: String?,
    val cover:ByteArray?,
    val publisher:String?,
    val readStatus: Int
) {
}