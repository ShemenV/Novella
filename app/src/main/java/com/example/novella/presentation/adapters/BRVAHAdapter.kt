package com.example.novella.presentation.adapters

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.novella.R
import com.example.novella.domain.Entities.Book
import com.squareup.picasso.Picasso

class BRVAHAdapter(books: MutableList<Book?>?) :
    BaseQuickAdapter<Book?, BaseViewHolder>(R.layout.book_item, books),
    LoadMoreModule {

    init {

    }

    override fun convert(holder: BaseViewHolder, item: Book?) {




        holder.setText(R.id.titleTextView, item?.title)
        if (item?.cover != null) {
            holder.setImageBitmap(R.id.coverImageView, item.cover?.let {
                android.graphics.BitmapFactory.decodeByteArray(
                    item.cover, 0,
                    it.size
                )
            })
        } else if (item?.coverUrl != null) {
            val imageView = holder.getView<ImageView>(R.id.coverImageView)
            Picasso.get()
                .load(item.coverUrl)
                .resize(0, 230)
                .centerCrop()
                .into(imageView)
            if(item.isSelect){
                val image = holder.getView<ImageView>(R.id.coverImageView)
                image.foreground = ColorDrawable(ContextCompat.getColor(image.context, R.color.select))
            }
        }

    }


}