package com.fajar.noteapp.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.noteapp.data.DataRepository
import com.fajar.noteapp.data.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(private val noteRepository: DataRepository): ViewModel() {

    //add to database function
    fun addNote(note:Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insert(note)
        }
    }

}