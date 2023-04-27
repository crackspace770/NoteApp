package com.fajar.noteapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fajar.noteapp.R
import com.fajar.noteapp.data.Note
import com.fajar.noteapp.ui.detail.DetailActivity
import com.fajar.noteapp.utils.NOTE_ID

import java.util.*

class NoteAdapter(
    private val clickListener: (Note) -> Unit
) : PagedListAdapter<Note, NoteAdapter.NoteViewHolder>(DIFF_CALLBACK) {

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

    override fun getItemViewType(position: Int): Int {
        ItemDecoration(position)
        return super.getItemViewType(position)
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_note, viewGroup, false)

        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position) as Note
        holder.bind(note, clickListener)
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvContent: TextView = itemView.findViewById(R.id.tvContent)
        //val cbComplete: CheckBox = itemView.findViewById(R.id.item_checkbox)
      //  private val tvDueDate: TextView = itemView.findViewById(R.id.item_tv_date)

        lateinit var getTask: Note

        fun bind(note: Note, clickListener: (Note) -> Unit) {
            getTask = note
            tvTitle.text = note.subject
            tvContent.text = note.content
         //   tvDueDate.text = DateConverter.convertMillisToString(note.created)
            itemView.setOnClickListener {
                val detailIntent = Intent(itemView.context, DetailActivity::class.java)
                detailIntent.putExtra(NOTE_ID, note.id)
                itemView.context.startActivity(detailIntent)
            }

        }
    }


    interface OnItemClickCallback {
        fun onItemClicked(data: Note)
    }


}
