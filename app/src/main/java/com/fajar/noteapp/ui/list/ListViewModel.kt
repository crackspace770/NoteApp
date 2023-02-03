package com.fajar.noteapp.ui.list

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.fajar.noteapp.data.DataRepository
import com.fajar.noteapp.data.Note
import kotlinx.coroutines.launch

class ListViewModel(private val noteRepository: DataRepository): ViewModel() {

    private val _noteId = MutableLiveData<Int>()
    val note: LiveData<PagedList<Note>> = _noteId.switchMap { input ->
        noteRepository.getAllNotes(input)
    }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            noteRepository.delete(note)
        }
    }

}