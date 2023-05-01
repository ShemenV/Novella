package com.example.novella.di

import com.example.novella.Domain.Usecases.GetReadBooksListUseCase
import com.example.novella.Presentation.Fragments.ViewModels.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModule = module {
    viewModel<MainFragmentViewModel>{
        MainFragmentViewModel(getReadBooksListUseCase = get())
    }
}