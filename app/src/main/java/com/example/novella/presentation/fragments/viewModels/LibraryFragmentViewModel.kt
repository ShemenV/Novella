package com.example.novella.presentation.fragments.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.Entities.SortOrders
import com.example.novella.domain.Entities.SortParams
import com.example.novella.domain.Entities.SortTypes
import com.example.novella.domain.usecases.DeleteBookUseCase
import com.example.novella.domain.usecases.GetReadBooksListUseCase
import com.example.novella.domain.usecases.GetSortParamsUseCase
import com.example.novella.domain.usecases.SaveSortParamsUseCase
import kotlinx.coroutines.launch

class LibraryFragmentViewModel(
    private val getReadBooksListUseCase: GetReadBooksListUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
    private val saveSortParamsUseCase: SaveSortParamsUseCase,
    private val getSortParamsUseCase: GetSortParamsUseCase
) : ViewModel() {

    var readBookList: MutableLiveData<MutableList<Book?>> = MutableLiveData<MutableList<Book?>>()
    var search: MutableLiveData<String?> = MutableLiveData("")
    var sortParams: MutableLiveData<SortParams> = MutableLiveData()


    fun initData() {

        sortParams.value = getSortParamsUseCase.execute()
        Log.e("SortParamsLibrary", sortParams.value.toString())
        readBookList.value = mutableListOf()
    }

    fun getAllBooks() {
        viewModelScope.launch {
            readBookList.value = getReadBooksListUseCase.execute()
        }
        Log.e("GettingBooks", readBookList.value.toString())
    }

    fun searchBooks(filter: String) {
        viewModelScope.launch {
            val allBooks = getReadBooksListUseCase.execute()
            readBookList.value =
                allBooks.filter { it?.title?.lowercase()?.contains(filter.lowercase()) ?: false }
                    .toMutableList()
            Log.e("hhghghgh",
                readBookList.value?.filter {
                    it?.title?.lowercase()?.startsWith(filter.lowercase()) ?: false
                }.toString()
            )
        }
    }

    fun sortBooks() {
        viewModelScope.launch {
            Log.e("sortList", sortParams.value?.sortOrder.toString())
        }
    }


    fun filterBook() {
        viewModelScope.launch {
            var books = getReadBooksListUseCase.execute()
            Log.e("size", sortParams.value?.filtersList.toString())
            when (sortParams.value?.filtersList?.size) {
                0 -> books = mutableListOf()
                1 -> books = books.filter { it?.readStatus == sortParams.value!!.filtersList[0] }
                    .toMutableList()
                2 -> books =
                    books.filter { it?.readStatus == sortParams.value!!.filtersList[0] || it?.readStatus == sortParams.value!!.filtersList[1] }
                        .toMutableList()
                3 -> books =
                    books.filter { it?.readStatus == sortParams.value!!.filtersList[0] || it?.readStatus == sortParams.value!!.filtersList[1] || it?.readStatus == sortParams.value!!.filtersList[2] }
                        .toMutableList()
            }


            when (sortParams.value?.sortOrder) {
                SortOrders.Descending -> {
                    when (sortParams.value?.sortType) {
                        SortTypes.TitleSort -> books =
                            books.sortedByDescending { it?.title }.toMutableList()
                        SortTypes.PageCountSort -> books =
                            books.sortedByDescending { it?.pageCount }.toMutableList()
                        else -> {}
                    }
                }

                SortOrders.Ascending -> {
                    when (sortParams.value?.sortType) {
                        SortTypes.TitleSort -> books =
                            books.sortedBy { it?.title }.toMutableList()
                        SortTypes.PageCountSort -> books =
                            books.sortedBy { it?.pageCount }.toMutableList()
                        else -> {}
                    }
                }
                else -> {
                    Log.e("sort", "NOTHING ")
                }
            }
            saveSortParamsUseCase.execute(sortParams.value!!)
            readBookList.value = books
        }
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch {
            deleteBookUseCase.execute(book)
            getAllBooks()
        }
    }
}