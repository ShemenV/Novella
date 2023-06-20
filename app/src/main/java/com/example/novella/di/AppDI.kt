package com.example.novella.di

import com.example.novella.presentation.fragments.viewModels.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

var appModule = module {
    viewModel<MainFragmentViewModel> {
        MainFragmentViewModel(getReadNowBooksUseCase = get())
    }

    viewModel<LibraryFragmentViewModel> {
        LibraryFragmentViewModel(
            getReadBooksListUseCase = get(named("getFromRoom")),
            deleteBookUseCase = get(named("deleteBook")),
            saveSortParamsUseCase = get(),
            getSortParamsUseCase = get()
        )
    }

    viewModel<SearchNewFragmentViewModel> {
        SearchNewFragmentViewModel(
            getBooksByNameUseCase = get(named("getBooksBuNameFromRetrofit")),
            getBooksIdsUseCase = get(named("getBooksIdsFromDb"))
        )
    }

    viewModel<BookFragmentViewModel> {
        BookFragmentViewModel(
            saveBookUseCase = get(named("saveNewBook")),
            getBooksIdsUseCase = get(named("getBooksIdsFromDb")),
            getAllGenresUseCase = get(),
            addBookGenresUseCase = get(),
            getBookGenresById = get(),
            deleteGenresByBookIdUseCase = get()
        )
    }

    viewModel<EditBookFragmentViewModel> {
        EditBookFragmentViewModel(
            saveBookUseCase = get(named("saveNewBook")),
            getBooksByIdUseCase = get(named("getBooksIdsFromGoogle")),
            getBooksIdsUseCase = get(named("getBooksIdsFromDb"))
        )
    }

    viewModel<AddBookFragmentViewModel> {
        AddBookFragmentViewModel(
            saveBookUseCase = get(named("saveNewBook")),
            getBooksByNameUseCase = get(named("getBooksBuNameFromRetrofit")),
            getBooksIdsUseCase = get(named("getBooksIdsFromDb"))
        )
    }

    viewModel<NotesFragmentViewModel> {
        NotesFragmentViewModel(
            getAllNotesUseCase = get(),
            saveNoteUseCase = get(),
            deleteNoteUseCase = get()
        )
    }

    viewModel<AddNoteFragmentViewModel> {
        AddNoteFragmentViewModel(
            getReadBooksListUseCase = get(named("getFromRoom")),
            saveNoteUseCase = get()
        )
    }

    viewModel<AnalysisFragmentViewModel>{
        AnalysisFragmentViewModel(
            getBooksCountUseCase = get()
        )
    }
}