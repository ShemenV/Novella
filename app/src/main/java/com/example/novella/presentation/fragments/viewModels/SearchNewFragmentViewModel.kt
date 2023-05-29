package com.example.novella.presentation.fragments.viewModels

import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.usecases.GetBooksByNameUseCase
import kotlinx.coroutines.launch

class SearchNewFragmentViewModel(private val getBooksByNameUseCase: GetBooksByNameUseCase):ViewModel() {

    val booksList: MutableLiveData<List<Book?>> = MutableLiveData()
    var searchName: String? = null

    fun getBooksByName(name: String?){
        viewModelScope.launch {
            booksList.value = getBooksByNameUseCase.execute(name, startIndex = 0)
            searchName = name
        }

    }

    fun updateBooksByStartIndex(startIndex: Int){
        viewModelScope.launch {
           booksList.value =  booksList.value?.plus(getBooksByNameUseCase.execute(searchName, startIndex = startIndex))
        }
    }

}