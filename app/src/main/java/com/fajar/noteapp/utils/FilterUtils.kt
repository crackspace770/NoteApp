package com.fajar.noteapp.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object FilterUtils {

    fun getFilteredQuery(filter: NoteFilterType): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM notes ")
        when (filter) {
            NoteFilterType.COMPLETED_TASKS -> {
                simpleQuery.append("WHERE completed = 1")
            }
            NoteFilterType.ACTIVE_TASKS -> {
                simpleQuery.append("WHERE completed = 0")
            }
            else -> {
                NoteFilterType.ALL_TASKS
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

}