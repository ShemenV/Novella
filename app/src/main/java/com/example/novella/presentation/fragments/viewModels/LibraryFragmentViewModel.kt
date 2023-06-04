package com.example.novella.presentation.fragments.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.usecases.GetReadBooksListUseCase
import kotlinx.coroutines.launch
import java.util.*

class LibraryFragmentViewModel(private val getReadBooksListUseCase: GetReadBooksListUseCase):ViewModel() {
    var readBookList: MutableLiveData<MutableList<Book?>> = MutableLiveData<MutableList<Book?>>()
    var search: MutableLiveData<String?> = MutableLiveData("")
    fun getAllBooks(){
        viewModelScope.launch {
            readBookList.value = getReadBooksListUseCase.execute()
        }
    }


    fun filterBooks(filter:String){
        viewModelScope.launch {
            val allBooks = getReadBooksListUseCase.execute()
            readBookList.value = allBooks.filter { it?.title?.lowercase()?.startsWith(filter.toLowerCase())?: false }.toMutableList()
            Log.e("hhghghgh",readBookList.value?.filter { it?.title?.toLowerCase()?.startsWith(filter.toLowerCase())?: false }.toString() )
        }
    }


    fun selectBook(position: Int){
        val bookList:MutableList<Book?> = readBookList.value!!
        val book = bookList?.get(position)
        book?.isSelect =true
        bookList?.set(position,book)
        Log.e("ddd",position.toString())
        readBookList.value = bookList
    }
}