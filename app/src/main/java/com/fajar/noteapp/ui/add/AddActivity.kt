package com.fajar.noteapp.ui.add

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
import java.text.SimpleDateFormat
import java.util.*

class AddActivity:AppCompatActivity() {

    private lateinit var viewModel: AddViewModel
    private lateinit var binding: ActivityAddBinding
    private lateinit var simpleDate: SimpleDateFormat
    private var created: Long = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.action_add)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[AddViewModel::class.java]

    //    binding.btnClose.setOnClickListener {
   //         val intent = Intent(this, ListActivity::class.java)
 //           startActivity(intent)
//
  //      }

        simpleDate = SimpleDateFormat("dd mm yyyy, h:mm a", Locale.getDefault())
        supportActionBar?.title = getString(R.string.created_title, simpleDate.format(viewModel.created))
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

                when{
                    binding.edTitle.text.toString().isEmpty() -> Toast.makeText(this,"Please fill in the title", Toast.LENGTH_SHORT).show()
                    binding.edContent.text.toString().isEmpty() -> Toast.makeText(this, "Please fill in the content", Toast.LENGTH_SHORT).show()

                }
                val title = titleNote.text.toString()
                val content = contentNote.text.toString()
                val note = Note(0, title, content,)
                Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()

                viewModel.addNote(note)
                super.onBackPressed()

                return true

            }
            else -> super.onOptionsItemSelected(item)
        }

    }

}