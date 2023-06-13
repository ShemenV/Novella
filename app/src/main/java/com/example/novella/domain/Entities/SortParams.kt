package com.example.novella.domain.Entities

data class SortParams(var sortType:SortTypes,
                      var sortOrder:SortOrders,
                      var filtersList: List<Int> = listOf(2,3,4)
)