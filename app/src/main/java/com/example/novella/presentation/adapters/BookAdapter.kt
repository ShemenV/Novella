package com.example.novella.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.*
import android.view.View.OnCreateContextMenuListener
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


class BookAdapter(private val context: Context,val listener:OnRecyclerViewItemClickListener):
    RecyclerView.Adapter<BookAdapter.BookViewHolder>()
{

    var data: List<Book?> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
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

        with(holder.binding){

            bookItemLayout.setOnClickListener {
                listener.onItemClick(position)
            }

            bookItemLayout.setOnCreateContextMenuListener { menu, view, contextMenuInfo ->
                menu.setHeaderTitle(R.string.selectContextAction);
                val menuItem1 = menu.add(0, view.getId(), 0, R.string.delete)
                menuItem1.setOnMenuItemClickListener {
                    if (book != null) {
                        listener.menuItem1Click(book)
                    }
                    true
                }

                val menuItem2 = menu.add(0, view.getId(), 0, R.string.edit)
                menuItem2.setOnMenuItemClickListener {
                    if (book != null) {
                        listener.menuItem2Click(book)
                    }
                    true
                }
            }

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

interface OnRecyclerViewItemClickListener{
    fun onItemClick(position:Int)
    fun menuItem1Click(book: Book)
    fun menuItem2Click(book: Book)
}