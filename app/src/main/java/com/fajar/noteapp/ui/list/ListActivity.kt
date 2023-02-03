package com.fajar.noteapp.ui.list

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fajar.noteapp.R
import com.fajar.noteapp.adapter.NoteAdapter
import com.fajar.noteapp.adapter.NotesAdapter
import com.fajar.noteapp.data.Note
import com.fajar.noteapp.databinding.AcitivtyListBinding
import com.fajar.noteapp.databinding.NoteListBinding
import com.fajar.noteapp.ui.ViewModelFactory
import com.fajar.noteapp.ui.add.AddActivity
import com.fajar.noteapp.ui.detail.DetailActivity
import com.fajar.noteapp.ui.detail.DetailActivity.Companion.EXTRA_NOTE

class ListActivity: AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: AcitivtyListBinding
    private lateinit var adapter: NoteAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcitivtyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[ListViewModel::class.java]

        recycler = findViewById(R.id.rv_note)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)

       // adapter = NotesAdapter {
     //       Intent(this, DetailActivity::class.java).apply {
    //            putExtra(EXTRA_NOTE)
    //            startActivity(this)
    //        }
    //    }

        viewModel.note.observe(this){
           it?.let{ note->
               adapter.submitList(Note)
               adapter.notifyDataSetChanged()
               recycler.adapter = adapter

           }
        }

        initAction()

    }

    private fun showRecyclerView(note: PagedList<Note>){

    }

    private fun initAction() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(0, ItemTouchHelper.RIGHT)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val habit = (viewHolder as NoteAdapter.ViewHolder).getNote
                viewModel.deleteNote(habit)
            }

        })
        itemTouchHelper.attachToRecyclerView(recycler)
    }

}