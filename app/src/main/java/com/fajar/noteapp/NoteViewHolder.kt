package com.fajar.noteapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fajar.noteapp.data.Note

class NoteViewHolder(view: View): RecyclerView.ViewHolder(view)  {

    private lateinit var note: Note

    //TODO 7 : Complete ViewHolder to show item(done)
    fun bind(note: Note, clickListener: (Note) -> Unit) {
        this.note = note

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

        itemView.setOnClickListener {
            clickListener(note)
        }
    }

    fun getCourse(): Note = note
}