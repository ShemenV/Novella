package com.example.novella.di

import com.example.novella.domain.usecases.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    factory<GetReadBooksListUseCase>(named("getFromRoom")) {
        GetReadBooksListUseCase(repository = get(named("room")))
    }

    factory<GetBooksByNameUseCase>(named("getBooksBuNameFromRetrofit")) {
        GetBooksByNameUseCase(repository = get(named("retrofit")))
    }

    factory<GetBooksIdsUseCase>(named("getBooksIdsFromDb")) {
        GetBooksIdsUseCase(repository = get(named("roomIds")))
    }

    factory<SaveBookUseCase>(named("saveNewBook")) {
        SaveBookUseCase(repository = get(named("roomIds")))
    }

    factory<DeleteBookUseCase>(named("deleteBook")) {
        DeleteBookUseCase(repository = get(named("roomIds")))
    }

    factory<GetBooksByIdUseCase>(named("getBooksIdsFromGoogle")) {
        GetBooksByIdUseCase(repository =  get())
    }

    factory<SaveSortParamsUseCase>(){
        SaveSortParamsUseCase(repository = get())
    }

    factory<GetSortParamsUseCase>(){
        GetSortParamsUseCase(repository = get())
    }

    factory<GetReadNowBooksUseCase>(){
        GetReadNowBooksUseCase(repositoryImpl =  get(named("roomIds")))
    }

    factory<GetAllGenresUseCase>(){
        GetAllGenresUseCase(repository = get())
    }

    factory<AddBookGenresUseCase>(){
        AddBookGenresUseCase(repository = get())
    }

    factory<GetBookGenresById>(){
        GetBookGenresById(repository = get())
    }

    factory<DeleteGenresByBookIdUseCase>(){
        DeleteGenresByBookIdUseCase(repository = get())
    }

    factory<GetAllNotesUseCase>()
    {
        GetAllNotesUseCase(repository = get())
    }

    factory<SaveNoteUseCase>()
    {
        SaveNoteUseCase(repository = get())
    }

    factory<DeleteNoteUseCase>{
        DeleteNoteUseCase(repository = get())
    }

}