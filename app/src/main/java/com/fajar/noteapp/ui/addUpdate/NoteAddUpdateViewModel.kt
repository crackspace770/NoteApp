package com.fajar.noteapp.ui.addUpdate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.noteapp.data.DataRepository
import com.fajar.noteapp.data.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteAddUpdateViewModel(private val noteRepository: DataRepository): ViewModel() {

    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insert(note)
        }
    }

    fun updateNote(note:Note){

        noteRepository.update(note)

    }

    fun delete(note: Note) {
        noteRepository.delete(note)
    }


}