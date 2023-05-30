package com.example.novella.presentation.adapters

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.novella.R
import com.example.novella.databinding.BookItemBinding
import com.example.novella.domain.Entities.Book
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.mikepenz.fastadapter.items.AbstractItem
import com.squareup.picasso.Picasso

open class BookItem(val book: Book?): AbstractBindingItem<BookItemBinding>() {

    override var identifier: Long
        get() = book.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = R.id.fastadapter_book_item


    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): BookItemBinding {
        return BookItemBinding.inflate(inflater,parent,false)
    }

    override fun bindView(binding: BookItemBinding, payloads: List<Any>) {
        val context = binding.coverImageView.context

        binding.titleTextView.text = book?.title

        if(book?.cover != null){
            binding.coverImageView.setImageBitmap(book?.cover?.let {
                BitmapFactory.decodeByteArray(book?.cover,0,
                    it.size)
            })
        }
        else if(book?.coverUrl != null){
            Picasso.get()
                .load(book?.coverUrl)
                .resize(0,230)
                .centerCrop()
                .into(binding.coverImageView)
            Log.e("++++++++++++++++++++++++++++++++++++",book?.coverUrl)
        }
    }

    override fun unbindView(binding: BookItemBinding) {
        binding.titleTextView.text = null
    }
}