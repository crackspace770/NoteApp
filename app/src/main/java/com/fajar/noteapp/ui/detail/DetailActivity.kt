package com.fajar.noteapp.ui.detail

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.fajar.noteapp.R
import com.fajar.noteapp.data.Note
import com.fajar.noteapp.databinding.ActivityDetailBinding
import com.fajar.noteapp.ui.ViewModelFactory
import com.fajar.noteapp.utils.NOTE_ID
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity:AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding
    private lateinit var simpleDate: SimpleDateFormat



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = intent.getIntExtra(NOTE_ID, 0)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        viewModel.setTaskId(intent)

        simpleDate = SimpleDateFormat("MMM d Y, h:mm a", Locale.getDefault())

        val subject = findViewById<TextView>(R.id.tvContent)
        val content = findViewById<TextView>(R.id.tvTitle)
        //val dueDate = findViewById<TextView>(R.id.detail_ed_due_date)

     //   val noteId = intent.getIntExtra(EXTRA_NOTE, 0)
  //      viewModel.setTaskId(noteId)

        viewModel.note.observe(this, {
            showDetail(it)
        })

        viewModel.note.observe(this){
            binding.apply {
                subject.text = it?.subject
                content.text = it?.content
            }
        }

    }

    private fun showDetail(note: Note?) {
        val tvSubject = findViewById<TextView>(R.id.tvTitle)
        val tvContent = findViewById<TextView>(R.id.tvContent)
        val tvDate = findViewById<TextView>(R.id.tvDate)


        note?.apply {

            tvSubject.text = this.subject
            //tvTime.text = timeFormat
            tvContent.text = this.content
          //  tvNote.text = this.note
        }
    }

}