package com.fajar.noteapp.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object FilterUtils {

    fun getFilteredQuery(filter: NoteFilterType): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM note ")
        when (filter) {
            NoteFilterType.LATEST_NOTES -> {
                simpleQuery.append("ORDER BY created DESC")
            }
            NoteFilterType.OLDEST_NOTES -> {
                simpleQuery.append("ORDER BY created ASC")
            }
            else -> {
                NoteFilterType.ALL_NOTES
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

}