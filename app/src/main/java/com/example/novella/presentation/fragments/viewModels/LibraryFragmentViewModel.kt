package com.example.novella.presentation.fragments.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.Domain.Entities.Book
import com.example.novella.Domain.Usecases.GetReadBooksListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class LibraryFragmentViewModel(private val getReadBooksListUseCase: GetReadBooksListUseCase):ViewModel() {
    var readBookList: MutableLiveData<List<Book?>> = MutableLiveData<List<Book?>>()
    var search: MutableLiveData<String?> = MutableLiveData("")
    fun getAllBooks(){
        viewModelScope.launch {
            readBookList.value = getReadBooksListUseCase.execute()
        }
    }


    fun filterBooks(filter:String){
        viewModelScope.launch {
            val allBooks = getReadBooksListUseCase.execute()
            readBookList.value = allBooks.filter { it?.title?.toLowerCase()?.startsWith(filter.toLowerCase())?: false }
            Log.e("hhghghgh",readBookList.value?.filter { it?.title?.toLowerCase()?.startsWith(filter.toLowerCase())?: false }.toString() )


        }
    }
}