package com.example.novella.presentation.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.example.novella.R
import com.example.novella.app.GlideApp
import com.example.novella.databinding.BookItemBinding
import com.example.novella.domain.Entities.Book
import com.squareup.picasso.Picasso


class BookAdapter(private val context: Context): RecyclerView.Adapter<BookAdapter.BookViewHolder>()
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
        var requestOptions = RequestOptions()

        with(holder.binding){

            val bitmap = BitmapFactory.decodeResource(context.resources,R.drawable.layla)

            titleTextView.text = book?.title

            if(book?.cover != null){
                coverImageView.setImageBitmap(book?.cover?.let {
                    BitmapFactory.decodeByteArray(book?.cover,0,
                        it.size)
                })
            }
            else if(book?.coverUrl != null){
                Picasso.get()
                    .load(book?.coverUrl)
                    .resize(0,230)
                    .centerCrop()
                    .into(holder.binding.coverImageView)
            }
        }



    }

    class BookViewHolder(val binding:BookItemBinding ): RecyclerView.ViewHolder(binding.root)
}