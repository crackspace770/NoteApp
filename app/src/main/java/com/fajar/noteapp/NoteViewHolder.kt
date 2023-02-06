package com.fajar.noteapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fajar.noteapp.data.Note
import java.text.SimpleDateFormat
import java.util.*

class NoteViewHolder(view: View): RecyclerView.ViewHolder(view)  {

    private lateinit var note: Note
    private val context = itemView.context
    //private val simpleDate = SimpleDateFormat("MMM d Y, h:mm a", Locale.getDefault())

    //TODO 7 : Complete ViewHolder to show item(done)
    fun bind(note: Note, clickListener: (Note) -> Unit) {
        this.note = note
        itemView.setOnClickListener { clickListener(note) }

        note.apply {
      //      val dayName = getByNumber(day)
      //      val timeFormat = String.format(timeString, dayName, startTime, endTime)

            val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
            val tvContent = itemView.findViewById<TextView>(R.id.tvContent)
            //val tvLecturer = itemView.findViewById<TextView>(R.id.tv_lecturer)

            tvTitle.text = this.subject
            tvContent.text = this.content
            //tvLecturer.text = this.lecturer
        }

    }

    fun getNote(): Note = note
}