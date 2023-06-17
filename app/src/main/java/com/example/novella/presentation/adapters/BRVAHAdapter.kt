package com.example.novella.presentation.adapters

import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
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



        val imageView = holder.getView<ImageView>(R.id.coverImageView)
        holder.setText(R.id.titleTextView, item?.title)
        if (item?.coverString != null) {
            val bitmap = BitmapFactory.decodeFile(item.coverString)
            holder.setImageBitmap(R.id.coverImageView,bitmap)
        } else if (item?.coverUrl != null) {

            Picasso.get()
                .load(item.coverUrl)
                .resize(0, 230)
                .centerCrop()
                .into(imageView)

        }

    }


}