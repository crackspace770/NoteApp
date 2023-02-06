package com.fajar.noteapp.ui.list

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fajar.noteapp.NoteViewHolder
import com.fajar.noteapp.R
import com.fajar.noteapp.adapter.NoteAdapter
import com.fajar.noteapp.data.Note
import com.fajar.noteapp.databinding.AcitivtyListBinding
import com.fajar.noteapp.ui.ViewModelFactory
import com.fajar.noteapp.ui.add.AddActivity
import com.fajar.noteapp.ui.detail.DetailActivity
import com.fajar.noteapp.utils.NOTE_ID

class ListActivity: AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: AcitivtyListBinding
    private val adapter: NoteAdapter by lazy {
        NoteAdapter(::onLetterClick)
    }

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

     //   adapter = NoteAdapter {
     //       Intent(this, DetailActivity::class.java).apply {
    //            putExtra(EXTRA_NOTE)
   //             startActivity(this)
   //         }
   //     }

        viewModel.note.observe(this){
           it?.let{
               adapter.submitList(it)
               adapter.notifyDataSetChanged()
               recycler.adapter = adapter
           }
        }

        initAction()

    }

  //  private fun showRecyclerView(note: PagedList<Note>){
//TODO 7 : Submit pagedList to adapter and update database when onCheckChange
 //       val noteAdapter = NoteAdapter
 //       {tasks, isChecked ->
 //           viewModel.completeTask(tasks, isChecked)
 //       }
 //       noteAdapter.submitList(note)
 //       noteAdapter.notifyDataSetChanged()
 //       recycler.adapter = noteAdapter
//    }

    private fun onLetterClick(note: Note) {
        Intent(this@ListActivity,DetailActivity::class.java).apply {
            putExtra(NOTE_ID,note.id)
        }.also { startActivity(it) }
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
                val habit = (viewHolder as NoteViewHolder).getNote()
                viewModel.deleteNote(habit)
            }

        })
        itemTouchHelper.attachToRecyclerView(recycler)
    }

}