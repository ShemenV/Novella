package com.example.novella.presentation.fragments

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
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
import androidx.navigation.fragment.navArgs
import com.example.novella.R
import com.example.novella.databinding.FragmentEditBookBinding
import com.example.novella.databinding.FragmentLibraryBinding
import com.example.novella.domain.Entities.Book
import com.example.novella.presentation.MAIN
import com.example.novella.presentation.fragments.viewModels.EditBookFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditBookFragment : Fragment() {
    lateinit var binding: FragmentEditBookBinding
    val viewModel by viewModel<EditBookFragmentViewModel>()
    val args: EditBookFragmentArgs by navArgs()
    val editableBook: Book by lazy { args.editableBook}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_edit_book, container, false)
        binding.vm = viewModel

        if(editableBook.id == null){
            binding.saveChangesButton.text = "Добавить"
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("EditableBook", editableBook.toString())

        viewModel.editableBookMutable.value = editableBook
        if(editableBook.pageCount != null){
            viewModel.editableBookPageCountStringMutable.value = editableBook.pageCount.toString()
        }
        else{
            viewModel.editableBookPageCountStringMutable.value = "0"
        }


        if (viewModel.editableBookMutable.value?.cover != null) {
            binding.editCoverImageView.setImageBitmap(viewModel.editableBookMutable.value?.cover?.let {
                android.graphics.BitmapFactory.decodeByteArray(
                    viewModel.editableBookMutable.value?.cover, 0,
                    it.size
                )
            })
        }
        viewModel.editableBookPageCountStringMutable.observe(viewLifecycleOwner, Observer {
            viewModel.setPageCount()
        })

        binding.saveChangesButton.setOnClickListener {
            Log.e("EditableBookResult",viewModel.editableBookMutable.value.toString())
            if(editableBook.author != null && !editableBook.author!!.isEmpty()){
                vallidateAuthor()
            }
            validateTitle()

            if(!binding.editAuthorTextInputLayout.isErrorEnabled && !binding.editTitleTextInputLayout.isErrorEnabled){
                viewModel.updateBook()
                MAIN.navController.navigate(R.id.action_editBookFragment_to_libraryFragment)
            }



        }

        binding.editCoverImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI)

            startActivityForResult(Intent.createChooser(intent,"Изображение для обложки"),
                SELECT_IMAGE)
        }
    }


    fun validateTitle():Boolean{
        var result:Boolean = true
        when{
            binding.editTitleTextInputLayout.editText!!.text.trim().isEmpty()->{
                binding.editTitleTextInputLayout.error = "Введите название книги"
                result = false
            }
            else -> {
                binding.editTitleTextInputLayout.isErrorEnabled = false
            }
        }
        return result
    }

    fun vallidateAuthor():Boolean{
        var result:Boolean = true
        when{
            binding.editAuthorTextInputLayout.editText!!.text.trim().isEmpty()->{
                binding.editAuthorTextInputLayout.error = "Введите имя автора"
                result = false
            }
            else -> {
                binding.editAuthorTextInputLayout.isErrorEnabled = false
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
        if (resultCode == RESULT_OK && requestCode == SELECT_IMAGE) {
            val imageUri = data?.data
            val reolver: ContentResolver = requireActivity().contentResolver
            val bytes = reolver.openInputStream(imageUri!!)?.readBytes()
            val bitmap =  BitmapFactory.decodeByteArray(
                bytes, 0,
                bytes!!.size
            )
            val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true)
            binding.editCoverImageView.setImageBitmap(resizedBitmap)

            viewModel.editableBookMutable.value?.cover = bytes
        }
    }

}