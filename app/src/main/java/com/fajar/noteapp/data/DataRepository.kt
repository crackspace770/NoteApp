package com.fajar.noteapp.data

import android.content.Context
import android.nfc.tech.MifareUltralight
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fajar.noteapp.utils.FilterUtils.getFilteredQuery
import com.fajar.noteapp.utils.NoteFilterType
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DataRepository(private val noteDao: NoteDao,
                     private val executorService: ExecutorService
                     = Executors.newSingleThreadExecutor()) {

    companion object {
        const val PAGE_SIZE = 30
        const val PLACEHOLDERS = true

        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(context: Context): DataRepository {
            return instance ?: synchronized(DataRepository::class.java) {
                if (instance == null) {
                    val database = NoteDatabase.getInstance(context)
                    instance = DataRepository(database.noteDao())
                }
                return instance as DataRepository
            }
        }
    }


    fun getAllNotes(filter: NoteFilterType): LiveData<PagedList<Note>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(MifareUltralight.PAGE_SIZE)
            .setPageSize(MifareUltralight.PAGE_SIZE)
            .build()

        return LivePagedListBuilder(noteDao.getAllNotes(getFilteredQuery(filter)), config).build()
    }

    fun getNote(id: Int):LiveData<Note>{
        return noteDao.getNote(id)
    }

    fun insert(note: Note) {
        executorService.execute { noteDao.insert(note) }
    }
    fun delete(note: Note) {
        executorService.execute { noteDao.delete(note) }
    }
    fun update(note: Note) {
        executorService.execute { noteDao.update(note) }
    }
}