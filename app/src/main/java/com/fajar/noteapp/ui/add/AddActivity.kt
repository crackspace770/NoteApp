package com.fajar.noteapp.ui.add

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fajar.noteapp.R
import com.fajar.noteapp.data.Note
import com.fajar.noteapp.databinding.ActivityAddBinding
import com.fajar.noteapp.ui.ViewModelFactory
import com.fajar.noteapp.ui.list.ListActivity

class AddActivity:AppCompatActivity() {

    private lateinit var viewModel: AddViewModel
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClose.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)

        }

    }

    //enable menu bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val titleNote = binding.edTitle
        val contentNote = binding.edContent

        return when (item.itemId){
            R.id.action_save ->{
                val factory = ViewModelFactory.getInstance(this)
                viewModel = ViewModelProvider(this, factory)[AddViewModel::class.java]

                val title = titleNote.text.toString()
                val content = contentNote.text.toString()
                val note = Note(0, title, content)

                Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()

                viewModel.addNote(note)
                super.onBackPressed()

                return true

            }
            else -> super.onOptionsItemSelected(item)
        }

    }

}