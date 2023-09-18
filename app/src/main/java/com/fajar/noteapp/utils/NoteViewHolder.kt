package com.fajar.noteapp.utils

import android.view.View
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.fajar.noteapp.R
import com.fajar.noteapp.data.Note
import java.text.SimpleDateFormat
import java.util.*

class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private lateinit var note: Note
    private val context = itemView.context
    private val simpleDate = SimpleDateFormat("MMM d Y, h:mm a", Locale.getDefault())

    lateinit var getNote: Note

    fun bind(letter: Note, clickListener: (Note) -> Unit) {
        this.note= letter
        itemView.setOnClickListener { clickListener(letter) }

       // val textOpenend = itemView.findViewById<TextView>(R.id.textOpened)
        val subjectText = itemView.findViewById<TextView>(R.id.tvTitleUpdate)
        val content = itemView.findViewById<TextView>(R.id.tvContentUpdate)


        subjectText.text = letter.subject
        content.text = letter.content


        }
    @VisibleForTesting
    fun getNote(): Note = note

    }

