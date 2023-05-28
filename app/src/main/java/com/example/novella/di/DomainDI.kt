package com.example.novella.di

import com.example.novella.Domain.Usecases.GetReadBooksListUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    factory<GetReadBooksListUseCase>(named("getFromRoom")) {
        GetReadBooksListUseCase(repository = get(named("room")))
    }

    factory<GetReadBooksListUseCase>(named("getFromRetrofit")) {
        GetReadBooksListUseCase(repository = get(named("retrofit")))
    }
}