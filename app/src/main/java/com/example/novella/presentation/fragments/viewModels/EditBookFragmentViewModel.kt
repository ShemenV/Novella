package com.example.novella.presentation.fragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.usecases.SaveBookUseCase
import kotlinx.coroutines.launch

class EditBookFragmentViewModel(private val saveBookUseCase: SaveBookUseCase):ViewModel() {
    val editableBookMutable: MutableLiveData<Book> = MutableLiveData()
    val editableBookPageCountStringMutable:MutableLiveData<String> = MutableLiveData()

    init {
        if(editableBookMutable.value?.pageCount != null){
            editableBookPageCountStringMutable.value = editableBookMutable.value!!.pageCount!!.toString()
        }
    }

    fun setPageCount(){
        editableBookMutable.value!!.pageCount = editableBookPageCountStringMutable.value?.toInt()
    }

    fun updateBook(){
        viewModelScope.launch {
            saveBookUseCase.execute(editableBookMutable.value!!)
        }
    }
}