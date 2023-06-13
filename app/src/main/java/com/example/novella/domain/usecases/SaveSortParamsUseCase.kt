package com.example.novella.domain.usecases

import com.example.novella.domain.Entities.SortParams
import com.example.novella.domain.Repositories.SortParamsRepository

class SaveSortParamsUseCase(private val repository: SortParamsRepository) {
    fun execute(sortParams: SortParams){
        repository.saveParams(sortParams)
    }
}