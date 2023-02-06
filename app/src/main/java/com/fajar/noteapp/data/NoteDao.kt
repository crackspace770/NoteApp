package com.fajar.noteapp.data

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SupportSQLiteQuery
import androidx.paging.DataSource
import androidx.room.*

@Dao
interface NoteDao {

  //  @RawQuery(observedEntities = [Note::class])
  //  fun getAllLetters(query: SupportSQLiteQuery): DataSource.Factory<Int, Note>

    @Query("SELECT * from note ORDER BY id ASC")
    fun getAllNotes(query: SupportSQLiteQuery): DataSource.Factory<Int, Note>

    @Query("select * from note where id = :noteId")
    fun getNote(noteId: Int): LiveData<Note>

    //displaying nearest data using WHERE on repository class
    @Query("SELECT * FROM note WHERE completed = 0 ORDER BY created ASC")
    fun getNearestNote(): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg note: Note)

    @Update
    fun update(note:Note)

    @Delete
    fun delete(note: Note)
}