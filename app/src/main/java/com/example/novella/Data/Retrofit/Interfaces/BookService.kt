package com.example.novella.Data.Retrofit.Interfaces

import com.example.novella.Data.Retrofit.Pojos.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("volumes")
    suspend fun getBookList(@Query("q") id:String): Response
}