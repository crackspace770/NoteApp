package com.fajar.noteapp.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.security.auth.Subject

@Entity(tableName = "note")
data class Note (

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @NonNull
    @ColumnInfo(name="subject")
    val subject: String,

    @NonNull
    @ColumnInfo(name = "content")
    val content: String,

  //  @NonNull
  //  @ColumnInfo(name="created")
  //  val created: Long

        )