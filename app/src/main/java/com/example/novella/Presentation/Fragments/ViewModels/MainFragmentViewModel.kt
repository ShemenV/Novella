package com.example.novella.Presentation.Fragments.ViewModels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.Data.Room.Repository.BookRepositoryImpl
import com.example.novella.Domain.Entities.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class MainFragmentViewModel(private val bookRepositoryImpl: BookRepositoryImpl): ViewModel() {


    val G: MutableLiveData<List<Book?>?> = MutableLiveData<List<Book?>?>()

    fun A(){
       viewModelScope.launch {
           G.value = bookRepositoryImpl.getBooks()
           Log.e("vm",G.toString())
           Log.e("bfdbdfb",G.toString())
       }
    }

}