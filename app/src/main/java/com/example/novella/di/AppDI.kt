package com.example.novella.di

import com.example.novella.presentation.fragments.viewModels.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

var appModule = module {
    viewModel<MainFragmentViewModel>{
        MainFragmentViewModel(getReadBooksListUseCase = get(named("getFromRoom")))
    }

    viewModel<LibraryFragmentViewModel>{
        LibraryFragmentViewModel(getReadBooksListUseCase = get(named("getFromRoom")), deleteBookUseCase = get(named("deleteBook")))
    }

    viewModel<SearchNewFragmentViewModel>{
        SearchNewFragmentViewModel(getBooksByNameUseCase = get(named("getBooksBuNameFromRetrofit")),
        getBooksIdsUseCase = get(named("getBooksIdsFromDb")))
    }

    viewModel<BookFragmentViewModel>{
        BookFragmentViewModel(saveBookUseCase =  get(named("saveNewBook")),
        getBooksIdsUseCase = get(named("getBooksIdsFromDb")))
    }

    viewModel<EditBookFragmentViewModel>{
        EditBookFragmentViewModel(saveBookUseCase =  get(named("saveNewBook")))
    }

}