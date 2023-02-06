package com.fajar.noteapp.adapter

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fajar.noteapp.NoteViewHolder
import com.fajar.noteapp.R
import com.fajar.noteapp.data.Note
import com.fajar.noteapp.databinding.ItemNoteBinding
import com.fajar.noteapp.databinding.NoteListBinding
import com.fajar.noteapp.ui.detail.DetailActivity
import kotlinx.coroutines.NonDisposableHandle.parent
import java.util.ArrayList

class NoteAdapter(
    private val clickListener: (Note) -> Unit
) : PagedListAdapter<Note, NoteViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback


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

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_note, viewGroup, false)

        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val letter = getItem(position) as Note
        holder.bind(letter, clickListener)
    }


    interface OnItemClickCallback {
        fun onItemClicked(data: Note)
    }


}