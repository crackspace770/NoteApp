package com.fajar.noteapp.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.fajar.noteapp.R
import com.fajar.noteapp.data.Note
import com.fajar.noteapp.databinding.ActivityDetailBinding
import com.fajar.noteapp.databinding.ActivityDetailsBinding
import com.fajar.noteapp.ui.ViewModelFactory
import com.fajar.noteapp.ui.add.AddViewModel
import com.fajar.noteapp.utils.NOTE_ID
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity:AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding
    private lateinit var simpleDate: SimpleDateFormat
    private var created: Long = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent.getIntExtra(NOTE_ID, 0)
        val factory = ViewModelFactory.getInstance(this)

        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        viewModel.setTaskId(intent)
        simpleDate = SimpleDateFormat("dd mm yyyy, h:mm a", Locale.getDefault())


        val subject = findViewById<TextView>(R.id.tvContent)
        val content = findViewById<TextView>(R.id.tvTitle)
     //   val dueDate = findViewById<TextView>(R.id.detail_ed_due_date)

        viewModel.note.observe(this) {
            showDetail(it)
        }

        viewModel.note.observe(this){
            binding.apply {
                subject.text = it?.subject
                content.text = it?.content
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val titleNote = binding.tvTitle
        val contentNote = binding.tvContent

        return when (item.itemId){
            R.id.action_save ->{
                val factory = ViewModelFactory.getInstance(this)
                viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]


                val title = titleNote.text.toString()
                val content = contentNote.text.toString()

                val note = Note(0, title, content, created)
                Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()

                viewModel.updateNote(note)
                super.onBackPressed()

                return true

            }
            else -> super.onOptionsItemSelected(item)
        }

    }


    private fun showDetail(note: Note?) {
        val tvSubject = findViewById<TextView>(R.id.tvTitle)
        val tvContent = findViewById<TextView>(R.id.tvContent)


        note?.apply {
            tvSubject.text = subject
            tvContent.text = content
        }
    }

}