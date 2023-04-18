package com.fajar.noteapp.utils

enum class NoteFilterType {
    /**
     * Do not filter tasks.
     */
    ALL_NOTES,

    /**
     * Filters only the active (not completed yet) tasks.
     */
    LATEST_NOTES,

    /**
     * Filters only the completed tasks.
     */
    OLDEST_NOTES
}