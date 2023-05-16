package com.fajar.noteapp.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fajar.noteapp.data.DataRepository

class DetailViewModelFactory(val context: Context, private val repository: Long, private val id: Int): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DataRepository::class.java, id.javaClass)
            .newInstance(DataRepository.getInstance(context), id)
    }
}