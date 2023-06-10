package com.example.novella.presentation.fragments.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.usecases.DeleteBookUseCase
import com.example.novella.domain.usecases.GetReadBooksListUseCase
import kotlinx.coroutines.launch

class LibraryFragmentViewModel(private val getReadBooksListUseCase: GetReadBooksListUseCase,
private val deleteBookUseCase: DeleteBookUseCase):ViewModel() {
    var readBookList: MutableLiveData<MutableList<Book?>> = MutableLiveData<MutableList<Book?>>()
    var search: MutableLiveData<String?> = MutableLiveData("")

    var selectedSortOrder:String = "TitleSort"
    var selectedSortType:String = "Descending"


    init {
        readBookList.value = mutableListOf()
    }
    fun getAllBooks() {
        viewModelScope.launch {
            readBookList.value = getReadBooksListUseCase.execute()
            readBookList.value = readBookList.value?.sortedBy { it?.title }?.toMutableList()

            Log.e("GettingBooks",readBookList.value.toString())
        }
    }


    fun filterBooks(filter:String){
        viewModelScope.launch {
            val allBooks = getReadBooksListUseCase.execute()
            readBookList.value = allBooks.filter { it?.title?.lowercase()?.contains(filter.lowercase())?: false }.toMutableList()
            Log.e("hhghghgh",readBookList.value?.filter { it?.title?.lowercase()?.startsWith(filter.lowercase())?: false }.toString() )
        }
    }


    fun sortBooksByType(sortType:String){
        when(sortType){
            "TitleSort" -> readBookList.value = readBookList.value?.sortedBy { it?.title }?.toMutableList()
            "PageCountSort" -> readBookList.value = readBookList.value?.sortedBy { it?.pageCount }?.toMutableList()
        }
    }

    fun sortBooksByOrder(sortOrder:String){
        when(sortOrder){
            "Descending" -> readBookList.value = readBookList.value?.asReversed()!!.toMutableList()
            "Ascending" -> readBookList.value = readBookList.value?.sortedBy { it?.pageCount }?.toMutableList()
        }
    }

    fun deleteBook(book: Book){
        viewModelScope.launch {
            deleteBookUseCase.execute(book)
            getAllBooks()
        }
    }
}