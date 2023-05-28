package com.example.novella.di

import com.example.novella.Data.Retrofit.Repository.RetrofitBookRepositoryImpl
import com.example.novella.Domain.Usecases.GetReadBooksListUseCase
import com.example.novella.presentation.fragments.viewModels.SearchNewFragmentViewModel
import com.example.novella.presentation.fragments.viewModels.LibraryFragmentViewModel
import com.example.novella.presentation.fragments.viewModels.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

var appModule = module {
    viewModel<MainFragmentViewModel>{
        MainFragmentViewModel(getReadBooksListUseCase = get(named("getFromRoom")))
    }

    viewModel<LibraryFragmentViewModel>{
        LibraryFragmentViewModel(getReadBooksListUseCase = get(named("getFromRoom")))
    }

    viewModel<SearchNewFragmentViewModel>{
        SearchNewFragmentViewModel(getReadBooksListUseCase = get(named("getFromRetrofit")))
    }
}