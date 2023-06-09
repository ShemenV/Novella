package com.example.novella.Data.Retrofit.Repository

import android.util.Log
import com.example.novella.Data.Retrofit.Interfaces.BookService
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.Repositories.BookRepository
import java.net.UnknownHostException

class RetrofitBookRepositoryImpl(private val bookApi: BookService): BookRepository {
    override suspend fun getBooks(): MutableList<Book?> {

        return bookApi.getBookList("r").items?.map { b ->
            var book:Book? = null
            if(b != null){
                book = b.ToBook()
            }
            book  }?.toMutableList()!!
    }

    override suspend fun getBooksByName(name: String?, startIndex: Int): MutableList<Book?>? {
       try {
           return (bookApi.getBookList(name,startIndex).items?.map { b ->
               var book:Book? = null
               if(b != null){
                   book = b.ToBook()
               }
               book
           } as MutableList<Book?>?)
       }
        catch (e:UnknownHostException){
            Log.e("Exception",e.toString())
            return mutableListOf(Book(title = "Exception", readedPages = 0, pageCount = 0))
        }
       catch (e:java.lang.Exception){
           Log.e("Exception",e.toString())
           return mutableListOf()
       }
    }



}