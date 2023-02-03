package com.fajar.noteapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

abstract fun noteDao(): NoteDao

    companion object {

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        /**
         * Returns an instance of Room Database.
         *
         * @param context application context
         * @return The singleton LetterDatabase
         */
        @JvmStatic
        fun getInstance(context: Context): NoteDatabase{
            if (INSTANCE == null){
                synchronized(NoteDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NoteDatabase::class.java, "note_database")
                        .build()
                }
            }
            return INSTANCE as NoteDatabase
        }
    }
}