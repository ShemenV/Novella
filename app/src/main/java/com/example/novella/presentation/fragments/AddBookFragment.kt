package com.example.novella.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.novella.R
import com.example.novella.databinding.FragmentAddBookBinding
import com.example.novella.presentation.fragments.viewModels.AddBookFragmentViewModel

class AddBookFragment : Fragment() {
    lateinit var binding: FragmentAddBookBinding
    private val viewModel by viewModels<AddBookFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_add_book, container, false)


        return binding.root

}
}