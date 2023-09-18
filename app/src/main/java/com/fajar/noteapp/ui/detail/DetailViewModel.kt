package com.fajar.noteapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fajar.noteapp.data.DataRepository
import com.fajar.noteapp.data.Note
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val noteRepository: DataRepository): ViewModel() {

  //  val notes: LiveData<Note> = _note

    private val _noteId = MutableLiveData<Int>()
    private val _note = _noteId.switchMap{ id ->
        noteRepository.getNote(id)
    }

    val note: LiveData<Note> = _note

    fun setTaskId(taskId: Int) {
        if (taskId == _noteId.value) {
            return
        }
        _noteId.value = taskId
    }

    fun updateNote(note:Note){

        noteRepository.update(note)

    }

}