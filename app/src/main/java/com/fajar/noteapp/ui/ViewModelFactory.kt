package com.fajar.noteapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fajar.noteapp.data.DataRepository
import com.fajar.noteapp.ui.add.AddViewModel
import com.fajar.noteapp.ui.addUpdate.NoteAddUpdateViewModel
import com.fajar.noteapp.ui.detail.DetailViewModel
import com.fajar.noteapp.ui.list.ListViewModel

class ViewModelFactory private constructor(private val noteRepository: DataRepository) :
    ViewModelProvider.Factory {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    DataRepository.getInstance(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(ListViewModel::class.java) -> {
                ListViewModel(noteRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(noteRepository) as T
            }
            modelClass.isAssignableFrom(AddViewModel::class.java) -> {
                AddViewModel(noteRepository) as T
            }
            modelClass.isAssignableFrom(NoteAddUpdateViewModel::class.java) ->{
                NoteAddUpdateViewModel(noteRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

}