package com.example.novella.presentation.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.novella.Data.Room.Dao.BooksDao
import com.example.novella.R
import com.example.novella.domain.Entities.Book

class BookSpinnerAdapter(val context: Context):BaseAdapter()
{
    var data: List<Book?> = emptyList()

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]!!
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.book_spinner_item, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }

        vh.label.text = data.get(position)?.title
        val bitmap = BitmapFactory.decodeFile(data.get(position)?.coverString)
        vh.img.setImageBitmap(bitmap)


        return view
    }

    private class ItemHolder(row: View?) {
        val label: TextView
        val img: ImageView

        init {
            label = row?.findViewById(R.id.bookTitleSpinnerTextView) as TextView
            img = row?.findViewById(R.id.bookSpinnerImageView) as ImageView
        }
    }
}