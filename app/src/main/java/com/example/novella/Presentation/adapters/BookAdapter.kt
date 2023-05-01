package com.example.novella.Presentation.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.novella.Domain.Entities.Book
import com.example.novella.R
import com.example.novella.databinding.BookItemBinding
import com.example.novella.databinding.FragmentMainBinding
import java.io.ByteArrayOutputStream

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
        val book: Book? = data[position]
        val context =  holder.itemView.context


        with(holder.binding){

            val bitmap = BitmapFactory.decodeResource(context.resources,R.drawable.layla)

            titleTextView.text = book?.title
            Log.e("==========",book?.cover?.toString() + "tttttttt")
            coverImageView.setImageBitmap(book?.cover?.let {
                BitmapFactory.decodeByteArray(book?.cover,0,
                    it.size)
            })

        }
    }

    class BookViewHolder(val binding:BookItemBinding ): RecyclerView.ViewHolder(binding.root)
}