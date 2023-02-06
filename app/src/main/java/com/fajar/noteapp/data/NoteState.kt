package com.fajar.noteapp.data

enum class NoteState {

    /**
     * Do not filter tasks.
     */
    ALL_NOTES,

    /**
     * Filters only the active (not completed yet) tasks.
     */
    ACTIVE_TASKS,

    /**
     * Filters only the completed tasks.
     */
    COMPLETED_TASKS
}