package com.example.novella.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.novella.R
import com.example.novella.databinding.NoteItemBinding
import com.example.novella.domain.Entities.Note

class NoteAdapter(private val context: Context, private val listener: OnNoteRecyclerViewClickListener): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

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

            noteItemLayout.setOnClickListener {
                listener.onItemClick(note!!)
            }
            noteItemLayout.setOnCreateContextMenuListener { menu, view, contextMenuInfo ->
                menu.setHeaderTitle(R.string.selectContextAction);
                val menuItem1 = menu.add(0, view.getId(), 0, R.string.delete)
                menuItem1.setOnMenuItemClickListener {
                    if( note != null) {
                        listener?.menuItemClick(note)
                    }
                    true
                }
            }


            noteTitleTextView.text = note?.title
            bookTitleTextView.text = note?.book?.title
            noteDateTextView.text= note?.addDate.toString()
        }
    }


    class NoteViewHolder(val binding: NoteItemBinding): RecyclerView.ViewHolder(binding.root)

}

interface OnNoteRecyclerViewClickListener{
    fun onItemClick(note: Note)
    fun menuItemClick(note: Note)
}