package com.example.novella.presentation.fragments.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.usecases.GetBooksByIdUseCase
import com.example.novella.domain.usecases.GetBooksIdsUseCase
import com.example.novella.domain.usecases.SaveBookUseCase
import kotlinx.coroutines.launch

class EditBookFragmentViewModel(private val saveBookUseCase: SaveBookUseCase,
private val getBooksByIdUseCase: GetBooksByIdUseCase,
private val getBooksIdsUseCase: GetBooksIdsUseCase):ViewModel() {
    val editableBookMutable: MutableLiveData<Book> = MutableLiveData()
    val editableBookPageCountStringMutable:MutableLiveData<String> = MutableLiveData()

    init {
        if(editableBookMutable.value?.pageCount != null){
            editableBookPageCountStringMutable.value = editableBookMutable.value!!.pageCount!!.toString()
        }
    }

    fun setPageCount(){

            if(editableBookPageCountStringMutable.value.equals("")){
                editableBookPageCountStringMutable.value = "0"
            }

        editableBookMutable.value!!.pageCount = editableBookPageCountStringMutable.value?.toInt()
    }

    fun updateBook(){
        viewModelScope.launch {
//            var newId:String = ""
//            if(editableBookMutable.value!!.id == null){
//                newId = generateId()
//                Log.e("tt",newId)
//                Log.e("ABOBA", getBooksByIdUseCase.execute("ZxlkehxIZmAC").toString())
//                while(getBooksByIdUseCase.execute(newId) != 0 && !getBooksIdsUseCase.execute().contains(newId)){
//                    newId = generateId()
//                }
//            }
//            editableBookMutable.value!!.id = newId
            saveBookUseCase.execute(editableBookMutable.value!!)
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