package com.example.novella.presentation.adapters

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.novella.R
import com.example.novella.databinding.ViewPageBookItemBinding
import com.example.novella.domain.Entities.Book
import com.squareup.picasso.Picasso

class MainViewPagerAdapter(private val listener: OnViewPagerItemClickListener):RecyclerView.Adapter<MainViewPagerAdapter.MainViewPagerViewHolder>() {
    var data: List<Book?> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewPagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewPageBookItemBinding.inflate(inflater, parent, false)
        return MainViewPagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MainViewPagerViewHolder, position: Int) {
        val book: Book? = data[position]

        with(holder.binding){

            bookItemLayout.setOnClickListener {
               listener.onItemClick(position)
            }

            titleTextView.text = book?.title

            if(book?.coverString == null && book?.cover == null && book?.coverUrl == null){
                val drawable: Drawable = coverImageView.context.resources.getDrawable(R.drawable.book_placeholder)
                coverImageView.setImageDrawable(drawable)
            }
            if(book?.coverString != null){
                Log.e("ssccscs",book.coverString.toString())
                val bitmap = BitmapFactory.decodeFile(book.coverString)

                coverImageView.setImageBitmap(bitmap)
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

    class MainViewPagerViewHolder(val binding: ViewPageBookItemBinding): RecyclerView.ViewHolder(binding.root)


}
interface OnViewPagerItemClickListener{
    fun onItemClick(position:Int)
}
