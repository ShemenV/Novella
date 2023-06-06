package com.example.novella.presentation.fragments

import android.os.Bundle
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.editableBookPageCountStringMutable.observe(viewLifecycleOwner, Observer {
            viewModel.setPageCount()
        })

        binding.saveChangesButton.setOnClickListener {
            viewModel.updateBook()
        }
    }

}