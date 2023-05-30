package com.example.novella.presentation.fragments.viewModels

import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.usecases.GetBooksByNameUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class SearchNewFragmentViewModel(private val getBooksByNameUseCase: GetBooksByNameUseCase):ViewModel() {

    val booksList: MutableLiveData<MutableList<Book?>?> = MutableLiveData()
    var searchName: String? = null
    var newBooksList:MutableList<Book?>?  = null

    var startIndex: Int = 0
    fun getBooksByName(name: String?){
        viewModelScope.launch {
            startIndex = 0
            booksList.value = getBooksByNameUseCase.execute(name, startIndex = startIndex)
            searchName = name
        }

    }


    fun getNextPageBook(): MutableList<Book?>?{
        startIndex+=10

        var books:MutableList<Book?>? = null
        viewModelScope.launch {
              newBooksList = getBooksByNameUseCase.execute(searchName, startIndex = startIndex)
        }
       Log.e("ABOBA",newBooksList.toString())

        return books

    }

}