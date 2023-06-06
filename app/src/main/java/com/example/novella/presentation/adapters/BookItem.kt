package com.example.novella.presentation.adapters


import android.graphics.BitmapFactory
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnCreateContextMenuListener
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import com.example.novella.R
import com.example.novella.databinding.BookItemBinding
import com.example.novella.domain.Entities.Book
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.squareup.picasso.Picasso


open class BookItem(val book: Book?, val contextMenuActions: ContextMenuActions): AbstractBindingItem<BookItemBinding>() {

    override var identifier: Long
        get() = book.hashCode().toLong()
        set(value) {}

    override val type: Int
        get() = com.example.novella.R.id.fastadapter_book_item


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
        }

        binding.bookItemLayout.setOnCreateContextMenuListener { menu, v, contextMenuInfo ->
            menu.setHeaderTitle(R.string.selectContextAction);
            val menuItem1 = menu.add(0, v.getId(), 0, R.string.delete)
            val menuItem2 = menu.add(0, v.getId(), 0, R.string.edit)

            menuItem1.setOnMenuItemClickListener {
                contextMenuActions.menuItem1Click()
                true
            }

            menuItem2.setOnMenuItemClickListener {
                contextMenuActions.menuItem2Click()
                true
            }
        }
    }

    override fun unbindView(binding: BookItemBinding) {
        binding.titleTextView.text = null
    }


}

interface ContextMenuActions{
    fun menuItem1Click()
    fun menuItem2Click()
}