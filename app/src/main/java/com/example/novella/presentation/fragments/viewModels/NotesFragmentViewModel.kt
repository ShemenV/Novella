package com.example.novella.presentation.fragments.viewModels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Note
import com.example.novella.domain.usecases.SaveNoteUseCase
import com.example.novella.domain.usecases.GetAllNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class NotesFragmentViewModel(private val getAllNotesUseCase: GetAllNotesUseCase,
private val saveNoteUseCase: SaveNoteUseCase):ViewModel() {

    val notesListMutable: MutableLiveData<MutableList<Note>> = MutableLiveData()

    fun setNotes(){
        viewModelScope.launch {
            notesListMutable.value = getAllNotesUseCase.execute()
            Log.e("Notes", getAllNotesUseCase.execute().toString())
        }
    }


}