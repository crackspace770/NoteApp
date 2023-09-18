package com.fajar.noteapp.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import javax.security.auth.Subject

@Entity(tableName = "note")
@Parcelize
data class Note (

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name="subject")
    var subject: String? = null,

    @ColumnInfo(name = "content")
    var content: String? = null,

    @ColumnInfo(name="created")
    var created: String? = null

        ):Parcelable