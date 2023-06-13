package com.example.novella.Data.Retrofit.Pojos

import com.example.novella.domain.Entities.Book
import com.google.gson.annotations.SerializedName

data class ItemsItem(
	@field:SerializedName("volumeInfo")
	val volumeInfo: VolumeInfo? = null,

	@field:SerializedName("id")
	val id: String? = null,
){
	fun ToBook(): Book {
		return Book(
			id = id!!,
			title = volumeInfo?.title,
			author = volumeInfo?.authors?.joinToString(", "),
			pageCount = volumeInfo?.pageCount!!,
			description = volumeInfo?.description,
			cover = null,
			publisher = volumeInfo?.publisher,
			coverUrl = volumeInfo?.imageLinks?.thumbnail,
			readStatus = 1,
			readedPages = 0
		)
	}
}