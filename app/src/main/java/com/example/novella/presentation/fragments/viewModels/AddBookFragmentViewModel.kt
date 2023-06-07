package com.example.novella.presentation.fragments.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.usecases.GetBooksByIdUseCase
import com.example.novella.domain.usecases.GetBooksByNameUseCase
import com.example.novella.domain.usecases.GetBooksIdsUseCase
import com.example.novella.domain.usecases.SaveBookUseCase
import kotlinx.coroutines.launch

class AddBookFragmentViewModel (private val saveBookUseCase: SaveBookUseCase,
                                private val getBooksByNameUseCase: GetBooksByNameUseCase,
                                private val getBooksIdsUseCase: GetBooksIdsUseCase
): ViewModel() {
    val addBookMutable: MutableLiveData<Book> = MutableLiveData()
    val addBookPageCountStringMutable: MutableLiveData<String> = MutableLiveData()


    init {
        if(addBookMutable.value?.pageCount != null){
            addBookPageCountStringMutable.value = addBookMutable.value!!.pageCount!!.toString()
        }
    }

    fun setPageCount(){
        if(addBookPageCountStringMutable.value.equals("")){
            addBookPageCountStringMutable.value = "0"
        }
        addBookMutable.value!!.pageCount = addBookPageCountStringMutable.value?.toInt()
    }

    fun updateBook(){
        viewModelScope.launch {
            var newId:String = ""
            if(addBookMutable.value!!.id == null){
                newId = generateId()
                Log.e("tt",newId)
                Log.e("ABOBA", getBooksByNameUseCase.execute("ZxlkehxIZmAC").toString())
                while(getBooksByNameUseCase.execute(newId) != null && !getBooksIdsUseCase.execute().contains(newId)){
                    newId = generateId()
                }
            }
            addBookMutable.value!!.id = newId
            saveBookUseCase.execute(addBookMutable.value!!)
        }
    }

    fun  generateId():String{
        val symbols = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890"
        var id = ""

        while(id.length<12){
            id = id+symbols[(0..symbols.length-1).random()]
        }
        return id
    }
}