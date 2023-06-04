package com.example.novella.presentation.fragments

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.novella.R
import com.example.novella.databinding.FragmentBookBinding
import com.example.novella.domain.Entities.Book
import com.example.novella.presentation.fragments.viewModels.BookFragmentViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


class BookFragment : Fragment(), ModalBottomSheetFragment.ModalBottomListener {
    lateinit var binding: FragmentBookBinding
    private val viewModel by viewModel<BookFragmentViewModel>()
    val args: BookFragmentArgs by navArgs()
    val selectBook: Book by lazy { args.book }

    lateinit var chooseStatusListener:ModalBottomSheetFragment.ModalBottomListener
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_book, container, false)
        binding.vm = viewModel
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("SelectedBook",args.book.toString())

        chooseStatusListener = this

        viewModel.setSelectedBook(selectBook)

        if(selectBook.cover != null){
            binding.coverImageView.setImageBitmap(selectBook?.cover?.let {
                BitmapFactory.decodeByteArray(selectBook?.cover,0,
                    it.size)
            })
        }

        else if(selectBook?.coverUrl != null){

            Picasso.get()
                .load(selectBook.coverUrl)
                .resize(0,230)
                .centerCrop()
                .into(binding.coverImageView)
        }

        changeBooksStatusButton(binding.changeBookStatusExtendedFAB,selectBook.readStatus)

        val modalBottomSheet = ModalBottomSheetFragment(chooseStatusListener,selectBook.readStatus)



        binding.changeBookStatusExtendedFAB.setOnClickListener {
            modalBottomSheet.show(activity?.supportFragmentManager!!, ModalBottomSheetFragment.TAG)
        }



        viewModel.selectedReadStatus.observe(viewLifecycleOwner, Observer {

            if(it != null){
                changeBooksStatusButton(binding.changeBookStatusExtendedFAB,it)
                viewModel.selectedBookMutable.value?.readStatus = it
                viewModel.saveBook()
            }

        })


    }

    fun changeBooksStatusButton(button:Button,status: Int){
        when(status){
            1 -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.noInLists)
                button.text = "Не добавлена"
            }
            2 -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.wantRead)
                button.text = "Хочу прочитать"
            }
            3 -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.readed)
                button.text = "Прочитано"
            }
            4 -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.readNow)
                button.text = "Читаю сейчас"
            }
        }
    }

    override fun chooseReadStatusClick(seletedStatus: Int) {
        viewModel.setSelectedReadStatus(seletedStatus)
    }
}