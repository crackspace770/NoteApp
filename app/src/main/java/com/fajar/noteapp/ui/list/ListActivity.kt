package com.fajar.noteapp.ui.list

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
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
import com.fajar.noteapp.ui.detail.DetailActivity.Companion.EXTRA_NOTE
import com.fajar.noteapp.utils.NOTE_ID
import com.fajar.noteapp.utils.NoteFilterType

class ListActivity: AppCompatActivity() {

    private lateinit var rvNote: RecyclerView
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: AcitivtyListBinding
    private val noteAdapter: NoteAdapter by lazy {
        NoteAdapter(::onLetterClick)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcitivtyListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))


        binding.fab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[ListViewModel::class.java]

        rvNote = findViewById(R.id.rv_note)
        rvNote.layoutManager = LinearLayoutManager(this)
        rvNote.setHasFixedSize(true)

       // noteAdapter = NoteAdapter {
      //      Intent(this, DetailActivity::class.java).apply {
     //           putExtra(EXTRA_NOTE)
    //            startActivity(this)
    //        }
    //    }

        viewModel.note.observe(this){
           it?.let{
               noteAdapter.submitList(it)
               noteAdapter.notifyDataSetChanged()
               rvNote.adapter = noteAdapter
           }
        }

        setUpRecyclerView()
        noteList()
        initAction()

    }

    private fun noteList(){
        viewModel.note.observe(this) {
            Log.d("Letter", it.toString())
            noteAdapter.submitList(it)
        }
    }

    private fun setUpRecyclerView(){

        rvNote = findViewById(R.id.rv_note)
        rvNote.apply {
            layoutManager = GridLayoutManager(this@ListActivity,2)
            adapter = adapter
        }
    }



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
        itemTouchHelper.attachToRecyclerView(rvNote)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.action_filter -> {
                showFilteringPopUpMenu()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showFilteringPopUpMenu(){

        val view = findViewById<View>(R.id.action_filter) ?: return
        PopupMenu(this, view).run {
            menuInflater.inflate(R.menu.filter_note, menu)

            //filter from view model
            setOnMenuItemClickListener {
                viewModel.filter(
                    when (it.itemId) {
                        R.id.latest -> NoteFilterType.LATEST_NOTES
                        R.id.oldest-> NoteFilterType.OLDEST_NOTES
                        else -> NoteFilterType.ALL_NOTES
                    }
                )
                true
            }
            show()
        }

    }

}