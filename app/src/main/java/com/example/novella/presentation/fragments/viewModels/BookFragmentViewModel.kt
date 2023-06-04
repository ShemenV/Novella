package com.example.novella.presentation.fragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.usecases.GetBooksIdsUseCase
import com.example.novella.domain.usecases.SaveBookUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.URL
import java.net.URLConnection

class BookFragmentViewModel(
    val saveBookUseCase: SaveBookUseCase,
    private val getBooksIdsUseCase: GetBooksIdsUseCase
) : ViewModel() {
    val selectedBookMutable: MutableLiveData<Book> = MutableLiveData<Book>()
    val selectedReadStatus: MutableLiveData<Int> = MutableLiveData<Int>()

    init {
        selectedReadStatus.value = selectedBookMutable.value?.readStatus
    }

    fun setSelectedBook(book: Book) {
        selectedBookMutable.value = book
    }

    fun setSelectedReadStatus(status: Int) {
        selectedReadStatus.value = status
        selectedBookMutable.value?.readStatus = selectedReadStatus.value!!
    }


    fun saveBook() {
        viewModelScope.launch {
            saveBookUseCase.execute(selectedBookMutable.value!!)
        }
    }
}