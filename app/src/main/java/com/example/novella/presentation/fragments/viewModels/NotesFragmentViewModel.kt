package com.example.novella.presentation.fragments.viewModels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Note
import com.example.novella.domain.usecases.AddNoteUseCase
import com.example.novella.domain.usecases.GetAllNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.getScopeId
import java.time.LocalDate

class NotesFragmentViewModel(private val getAllNotesUseCase: GetAllNotesUseCase,
private val addNoteUseCase: AddNoteUseCase):ViewModel() {

    val notesListMutable: MutableLiveData<MutableList<Note>> = MutableLiveData()

    fun setNotes(){
        viewModelScope.launch {
            notesListMutable.value = getAllNotesUseCase.execute()
            Log.e("Notes", getAllNotesUseCase.execute().toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveNote(){
        viewModelScope.launch(Dispatchers.IO) {
            addNoteUseCase.execute(Note(12,"wefwef","fewfwefwe", book = null, LocalDate.now()))
        }
    }

}