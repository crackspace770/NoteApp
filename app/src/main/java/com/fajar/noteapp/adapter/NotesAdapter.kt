package com.fajar.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.fajar.noteapp.NoteViewHolder
import com.fajar.noteapp.R
import com.fajar.noteapp.data.Note

class NotesAdapter(private val clickListener: (Note) -> Unit) :
    PagedListAdapter<Note, NoteViewHolder>(DIFF_CALLBACK) {


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }
        }
    }

    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val course = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(course)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val course = getItem(position) as Note
        holder.bind(course, clickListener)
    }

}