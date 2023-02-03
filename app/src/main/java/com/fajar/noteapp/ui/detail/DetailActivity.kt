package com.fajar.noteapp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fajar.noteapp.data.Note
import com.fajar.noteapp.databinding.ActivityDetailBinding
import com.fajar.noteapp.ui.ViewModelFactory
import com.fajar.noteapp.ui.add.AddViewModel

class DetailActivity:AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding

    companion object{
        const val EXTRA_NOTE= "extra_note"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val noteId = intent.getIntExtra(EXTRA_NOTE, 0)
        viewModel.setTaskId(noteId)


        viewModel.note.observe(this){
            binding.apply {
                tvTitle.text = it?.subject
                tvContent.text = it?.content
            }
        }

    }

}