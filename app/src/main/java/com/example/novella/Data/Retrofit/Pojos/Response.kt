package com.example.novella.Data.Retrofit.Pojos

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)