package com.example.novella.Presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.novella.Domain.Entities.Book
import com.example.novella.databinding.BookItemBinding
import com.example.novella.databinding.FragmentMainBinding

class BookAdapter: RecyclerView.Adapter<BookAdapter.BookViewHolder>()
{

    var data: List<Book?> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BookItemBinding.inflate(inflater, parent, false)

        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = data[position]
        val context =  holder.itemView.context

        with(holder.binding){
            textView2.text = book?.title
        }
    }

    class BookViewHolder(val binding:BookItemBinding ): RecyclerView.ViewHolder(binding.root)
}