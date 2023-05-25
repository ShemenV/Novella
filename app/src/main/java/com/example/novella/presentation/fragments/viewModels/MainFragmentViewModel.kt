package com.example.novella.presentation.fragments.viewModels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.Domain.Entities.Book
import com.example.novella.Domain.Usecases.GetReadBooksListUseCase
import kotlinx.coroutines.launch

class MainFragmentViewModel(private val getReadBooksListUseCase: GetReadBooksListUseCase): ViewModel() {


    val readBooksList: MutableLiveData<List<Book?>> = MutableLiveData<List<Book?>>()

    fun getReadBooks(){
       viewModelScope.launch {
           readBooksList.value = getReadBooksListUseCase.execute()

       }
    }

}