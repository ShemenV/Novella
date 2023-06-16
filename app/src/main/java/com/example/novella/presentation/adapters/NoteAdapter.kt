package com.example.novella.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.novella.databinding.NoteItemBinding
import com.example.novella.domain.Entities.Note

class NoteAdapter(private val context: Context): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    var data: List<Note?> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteItemBinding.inflate(inflater, parent, false)

        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note: Note? = data[position]

        with(holder.binding){
            noteTitleTextView.text = note?.title
            noteDateTextView.text= note?.addDate.toString()
            bookTitleTextView.text = note?.
        }
    }


    class NoteViewHolder(val binding: NoteItemBinding): RecyclerView.ViewHolder(binding.root)

}