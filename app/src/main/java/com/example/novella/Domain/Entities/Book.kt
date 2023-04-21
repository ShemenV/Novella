package com.example.novella.Domain.Entities

data class Book(
    val id: Long,
    val title: String,
    val author: String,
    val pageCount: Int,
    val description: String,
    val cover:String,
    val publisher:String
) {
}