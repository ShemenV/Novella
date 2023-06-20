package com.example.novella.presentation.fragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.usecases.GetBooksCountUseCase
import kotlinx.coroutines.launch

class AnalysisFragmentViewModel(
    private val getBooksCountUseCase: GetBooksCountUseCase,
): ViewModel() {

    val totalBooksCount: MutableLiveData<Int> = MutableLiveData<Int>()

    fun setBooksCount(){
        viewModelScope.launch {
            totalBooksCount.value = getBooksCountUseCase.execute()
        }
    }

}