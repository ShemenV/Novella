package com.example.novella.presentation.fragments.viewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.Entities.Note
import com.example.novella.domain.usecases.GetReadBooksListUseCase
import com.example.novella.domain.usecases.SaveNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.notifyAll

class AddNoteFragmentViewModel(
    private val getReadBooksListUseCase: GetReadBooksListUseCase,
    private val saveNoteUseCase: SaveNoteUseCase
): ViewModel() {

    val booksListMutable: MutableLiveData<MutableList<Book?>> = MutableLiveData<MutableList<Book?>>()
    var addingNoteMutable: MutableLiveData<Note> = MutableLiveData<Note>()
    val books: MutableList<Book> = mutableListOf()
    @RequiresApi(Build.VERSION_CODES.O)
    fun initLiveData(){
        viewModelScope.launch {
            booksListMutable.value = getReadBooksListUseCase.execute()
        }
        addingNoteMutable.value = Note()
    }

    fun saveNote(){
        viewModelScope.launch(Dispatchers.IO) {
            saveNoteUseCase.execute(addingNoteMutable.value!!)
        }
    }

    fun setSelctedNote(note: Note){
        addingNoteMutable.value = note
    }
}
