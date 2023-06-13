package com.example.novella.presentation.fragments.viewModels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.usecases.GetReadBooksListUseCase
import com.example.novella.domain.usecases.GetReadNowBooksUseCase
import kotlinx.coroutines.launch

class MainFragmentViewModel(private val getReadNowBooksUseCase: GetReadNowBooksUseCase): ViewModel() {

    val readNowBooksList: MutableLiveData<List<Book?>> = MutableLiveData<List<Book?>>()

    fun getReadBooks(){
       viewModelScope.launch {
           readNowBooksList.value = getReadNowBooksUseCase.execute()
       }
    }

}