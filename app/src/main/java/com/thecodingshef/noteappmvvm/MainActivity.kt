package com.thecodingshef.noteappmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), CellClickListener {

    lateinit var viewModel: NoteViewModel
    lateinit var input:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView=findViewById<RecyclerView>(R.id.recycle)
        input=findViewById(R.id.input)

        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=NotesAdapter(this,this)
        recyclerView.adapter=adapter

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
            .getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }

        })
    }

    override fun onCellClickListener(note: Note) {


        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} deleted",Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val inputText=input.text.toString()
        if(inputText.isNotEmpty()){
            viewModel.insertNote(Note(inputText))
            Toast.makeText(this,"${inputText} inserted",Toast.LENGTH_SHORT).show()
        }


    }
}