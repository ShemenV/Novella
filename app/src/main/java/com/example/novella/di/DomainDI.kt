package com.example.novella.di

import com.example.novella.domain.usecases.GetBooksByNameUseCase
import com.example.novella.domain.usecases.GetReadBooksListUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    factory<GetReadBooksListUseCase>(named("getFromRoom")) {
        GetReadBooksListUseCase(repository = get(named("room")))
    }

    factory<GetBooksByNameUseCase>(named("getBooksBuNameFromRetrofit")) {
        GetBooksByNameUseCase(repository = get(named("retrofit")))
    }
}