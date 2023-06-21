package com.example.novella.presentation.fragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.usecases.GetBooksCountUseCase
import com.example.novella.domain.usecases.GetReadBooksListUseCase
import com.example.novella.domain.usecases.GetTotalPagesCountUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnalysisFragmentViewModel(
    private val getBooksCountUseCase: GetBooksCountUseCase,
    private val getTotalPagesCountUseCase: GetTotalPagesCountUseCase,
    private val getReadBooksListUseCase: GetReadBooksListUseCase,
): ViewModel() {

    val totalBooksCount: MutableLiveData<Int> = MutableLiveData<Int>()
    val readNowBooksCount: MutableLiveData<Int> = MutableLiveData<Int>()
    val wantReadBooksCount: MutableLiveData<Int> = MutableLiveData<Int>()
    val readedBooksCount: MutableLiveData<Int> = MutableLiveData<Int>()
    val totalPagesCount: MutableLiveData<Int?> = MutableLiveData<Int?>()
    val booksList: MutableLiveData<MutableList<Book?>> = MutableLiveData()


    fun setBooksList(){
        viewModelScope.launch {
            booksList.value = getReadBooksListUseCase.execute()
        }
    }

    fun setBooksCount(){
        viewModelScope.launch {
            totalBooksCount.value = getBooksCountUseCase.execute()
            readedBooksCount.value = getBooksCountUseCase.execute(3)
            wantReadBooksCount.value = getBooksCountUseCase.execute(4)
            readNowBooksCount.value  = getBooksCountUseCase.execute(2)
        }
    }

    fun setTotalPagesCount(){
        viewModelScope.launch {
            totalPagesCount.value = getBooksCountUseCase.execute()
        }
    }

}