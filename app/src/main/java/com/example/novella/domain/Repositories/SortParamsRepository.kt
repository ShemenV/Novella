package com.example.novella.domain.Repositories

import com.example.novella.domain.Entities.SortParams

interface SortParamsRepository {
    fun saveParams(sortParams: SortParams)
    fun getParams(): SortParams?
}