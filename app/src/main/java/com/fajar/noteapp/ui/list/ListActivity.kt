package com.fajar.noteapp.ui.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fajar.noteapp.R
import com.fajar.noteapp.adapter.NoteAdapter
import com.fajar.noteapp.data.Note
import com.fajar.noteapp.databinding.AcitivtyListBinding
import com.fajar.noteapp.ui.ViewModelFactory
import com.fajar.noteapp.ui.add.AddActivity
import com.fajar.noteapp.ui.addUpdate.NoteAddUpdateActivity
import com.fajar.noteapp.ui.detail.DetailActivity
import com.fajar.noteapp.utils.NOTE_ID
import com.fajar.noteapp.utils.NoteFilterType

class ListActivity: AppCompatActivity() {

    private lateinit var rvNote: RecyclerView
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: AcitivtyListBinding
    private val noteAdapter: NoteAdapter by lazy {
        NoteAdapter(::onLetterClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcitivtyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProviders.of(this, factory)[ListViewModel::class.java]

        setUpRecyclerView()
        noteList()
        initAction()
        showEmptyDataState()
        addMenu()
    }

    private fun addMenu(){
        binding.addButton.setOnClickListener {
            val intent = Intent(this@ListActivity, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun noteList(){
        viewModel.note.observe(this) {
            Log.d("Note", it.toString())
            noteAdapter.submitList(it)
        }
    }

    private fun showEmptyDataState() {
        viewModel.note.observe(this) { notes ->
            val viewEmpty = findViewById<View>(R.id.viewEmpty)
            binding.apply {
                if (notes.isEmpty()) {
                    // Display the empty view and hide the RecyclerView
                    viewEmpty.visibility = View.VISIBLE
                    rvNote.visibility = View.GONE
                } else {
                    // Hide the empty view and show the RecyclerView
                    viewEmpty.visibility = View.GONE
                    rvNote.visibility = View.VISIBLE
                }
            }

        }
    }


    private fun setUpRecyclerView(){

        rvNote = findViewById(R.id.rv_note)
        rvNote.apply {
            layoutManager = GridLayoutManager(this@ListActivity, 2)
            adapter = noteAdapter
        }

    }

    private fun onLetterClick(note: Note) {
        val i = Intent(this, DetailActivity::class.java)
        i.putExtra(NOTE_ID, note.id)
        startActivity(i)
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
              //  val note = (viewHolder as NoteAdapter).getNote()
              //  viewModel.deleteNote(note)
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