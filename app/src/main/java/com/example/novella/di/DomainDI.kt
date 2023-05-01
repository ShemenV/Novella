package com.example.novella.di

import com.example.novella.Domain.Usecases.GetReadBooksListUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetReadBooksListUseCase> {
        GetReadBooksListUseCase(repository = get())
    }
}