package com.example.novella.presentation.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.novella.R
import com.example.novella.databinding.FragmentAddBookBinding
import com.example.novella.domain.Entities.Book
import com.example.novella.presentation.MAIN
import com.example.novella.presentation.fragments.viewModels.AddBookFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddBookFragment : Fragment() {
    lateinit var binding: FragmentAddBookBinding
    val viewModel by viewModel<AddBookFragmentViewModel>()
    val addBook: Book = Book()
    var resultBitmap :Bitmap? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_add_book, container, false)
        binding.vm = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AddBook", addBook.toString())

        viewModel.addBookMutable.value = addBook
        if(addBook.pageCount != null){
            viewModel.addBookPageCountStringMutable.value = addBook.pageCount.toString()
        }
        else{
            viewModel.addBookPageCountStringMutable.value = "0"
        }


        if (viewModel.addBookMutable.value?.coverString != null) {
            val bitmap = BitmapFactory.decodeFile(viewModel.addBookMutable.value?.coverString)
            binding.addCoverImageView.setImageBitmap(bitmap)
        }

        viewModel.imageMutable.observe(viewLifecycleOwner,Observer{
            if(it != null){
                binding.addCoverImageView.setImageBitmap(it)
            }
        })

        viewModel.addBookPageCountStringMutable.observe(viewLifecycleOwner, Observer {
            viewModel.setPageCount()
        })

        binding.addBookButton.setOnClickListener {
            Log.e("AddBookResult",viewModel.addBookMutable.value.toString())
            validateTitle()
            vallidateAuthor()
            if(!binding.addAuthorTextInputLayout.isErrorEnabled && !binding.addTitleTextInputLayout.isErrorEnabled){
                viewModel.updateBook()
                val handler = android.os.Handler()

                handler.postDelayed({ MAIN.navController.navigate(R.id.libraryFragment) }, 1000)
            }
        }

        binding.addCoverImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)

            startActivityForResult(
                Intent.createChooser(intent,"Изображение для обложки"),
                SELECT_IMAGE)
        }
    }


    fun validateTitle():Boolean{
        var result:Boolean = true
        when{
            binding.addTitleTextInputLayout.editText!!.text.trim().isEmpty()->{
                binding.addTitleTextInputLayout.error = "Введите название книги"
                result = false
            }
            else -> {
                binding.addTitleTextInputLayout.isErrorEnabled = false
            }
        }
        return result
    }

    fun vallidateAuthor():Boolean{
        var result:Boolean = true
        when{
            binding.addAuthorTextInputLayout.editText!!.text.trim().isEmpty()->{
                binding.addAuthorTextInputLayout.error = "Введите имя автора"
                result = false
            }
            else -> {
                binding.addAuthorTextInputLayout.isErrorEnabled = false
            }
        }
        return result
    }
    companion object {
        const val  SELECT_IMAGE = 100
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("Recycle")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_IMAGE) {
            val imageUri = data?.data
            val reolver: ContentResolver = requireActivity().contentResolver
            val bytes = reolver.openInputStream(imageUri!!)?.readBytes()
            val bitmap =  BitmapFactory.decodeByteArray(
                bytes, 0,
                bytes!!.size
            )

            viewModel.imageMutable.value = bitmap
            Log.e("ABOBA",viewModel.imageMutable.value.toString())
        }
    }
}