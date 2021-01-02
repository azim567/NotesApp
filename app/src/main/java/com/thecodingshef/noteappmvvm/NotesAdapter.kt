package com.thecodingshef.noteappmvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(val context:Context, val listener: CellClickListener) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    val notesList=ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view= LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.noteText.text=notesList.get(position).text

        holder.deleteBtn.setOnClickListener(){
            listener.onCellClickListener(notesList.get(position))
        }
    }

    override fun getItemCount(): Int {

        return notesList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val noteText:TextView=itemView.findViewById(R.id.noteText)
        val deleteBtn:ImageView=itemView.findViewById(R.id.delete_btn)

    }

    fun updateList(newList:List<Note>){
        notesList.clear()
        notesList.addAll(newList)

        notifyDataSetChanged()
    }

}

interface CellClickListener{
    fun onCellClickListener(note: Note)
}