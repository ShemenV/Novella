package com.example.novella.domain.usecases

import android.util.Log
import com.example.novella.domain.Entities.SortOrders
import com.example.novella.domain.Entities.SortParams
import com.example.novella.domain.Entities.SortTypes
import com.example.novella.domain.Repositories.SortParamsRepository

class GetSortParamsUseCase(private val repository: SortParamsRepository) {
    fun execute(): SortParams? {

       val b = repository.getParams()
        return b
    }
}