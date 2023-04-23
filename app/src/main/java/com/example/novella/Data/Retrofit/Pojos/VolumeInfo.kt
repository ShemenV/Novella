package com.example.novella.Data.Retrofit.Pojos

import com.example.novella.Domain.Entities.Book
import com.google.gson.annotations.SerializedName

data class VolumeInfo(
	@field:SerializedName("pageCount")
	val pageCount: Int? = null,

	@field:SerializedName("printType")
	val printType: String? = null,

	@field:SerializedName("previewLink")
	val previewLink: String? = null,

	@field:SerializedName("canonicalVolumeLink")
	val canonicalVolumeLink: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("imageLinks")
	val imageLinks: ImageLinks? = null,

	@field:SerializedName("subtitle")
	val subtitle: String? = null,

	@field:SerializedName("publisher")
	val publisher: String? = null,

	@field:SerializedName("publishedDate")
	val publishedDate: String? = null,

	@field:SerializedName("categories")
	val categories: List<String?>? = null,

	@field:SerializedName("maturityRating")
	val maturityRating: String? = null,

	@field:SerializedName("allowAnonLogging")
	val allowAnonLogging: Boolean? = null,

	@field:SerializedName("contentVersion")
	val contentVersion: String? = null,

	@field:SerializedName("authors")
	val authors: List<String?>? = null,

	@field:SerializedName("infoLink")
	val infoLink: String? = null
){

}

