package com.example.novella.presentation.fragments.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.usecases.DeleteBookUseCase
import com.example.novella.domain.usecases.GetReadBooksListUseCase
import kotlinx.coroutines.launch
import java.util.*

class LibraryFragmentViewModel(private val getReadBooksListUseCase: GetReadBooksListUseCase,
private val deleteBookUseCase: DeleteBookUseCase):ViewModel() {
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


    fun deleteBook(book: Book){
        viewModelScope.launch {
            deleteBookUseCase.execute(book)
            getAllBooks()
        }
    }
}