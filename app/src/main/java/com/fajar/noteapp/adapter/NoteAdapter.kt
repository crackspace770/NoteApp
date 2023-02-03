package com.fajar.noteapp.adapter

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fajar.noteapp.R
import com.fajar.noteapp.data.Note
import com.fajar.noteapp.databinding.ItemNoteBinding
import com.fajar.noteapp.databinding.NoteListBinding
import com.fajar.noteapp.ui.detail.DetailActivity
import java.util.ArrayList

class NoteAdapter(private val listNote: ArrayList<Note>)
    : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(var binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

        lateinit var getNote: Note

        fun bind(note: Note) {
            itemView.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_NOTE, note.id)
                it.context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val (title,content ) = listNote[position]
        viewHolder.binding.tvTitle.text = title.toString()
        viewHolder.binding.tvContent.text = content
        Log.e(ContentValues.TAG, "onBindViewHolder: login")
        viewHolder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listNote[viewHolder.adapterPosition]) }

    }

    override fun getItemCount(): Int = listNote.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Note)
    }

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
}