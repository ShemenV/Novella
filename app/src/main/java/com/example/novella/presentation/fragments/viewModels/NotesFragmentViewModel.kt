package com.example.novella.presentation.fragments.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Note
import com.example.novella.domain.usecases.GetAllNotesUseCase
import kotlinx.coroutines.launch

class NotesFragmentViewModel(private val getAllNotesUseCase: GetAllNotesUseCase):ViewModel() {

    val notesListMutable: MutableLiveData<MutableList<Note>> = MutableLiveData()

    fun setNotes(){
        viewModelScope.launch {
            notesListMutable.value = getAllNotesUseCase.execute()
            Log.e("Notes", getAllNotesUseCase.execute().toString())
        }
    }

}