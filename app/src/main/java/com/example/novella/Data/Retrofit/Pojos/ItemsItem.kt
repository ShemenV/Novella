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
			id = null,
			title = volumeInfo?.title,
			author = volumeInfo?.authors?.joinToString(", "),
			pageCount = volumeInfo?.pageCount,
			description = volumeInfo?.subtitle,
			cover = null,
			publisher = volumeInfo?.publisher,
			coverUrl = volumeInfo?.imageLinks?.smallThumbnail,
			readStatus = 1
		)
	}
}