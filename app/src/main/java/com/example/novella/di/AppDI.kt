package com.example.novella.di

import com.example.novella.presentation.fragments.viewModels.LibraryFragmentViewModel
import com.example.novella.presentation.fragments.viewModels.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModule = module {
    viewModel<MainFragmentViewModel>{
        MainFragmentViewModel(getReadBooksListUseCase = get())
    }

    viewModel<LibraryFragmentViewModel>{
        LibraryFragmentViewModel(getReadBooksListUseCase = get())
    }
}