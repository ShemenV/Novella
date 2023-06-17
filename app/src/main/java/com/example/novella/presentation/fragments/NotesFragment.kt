package com.example.novella.presentation.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.novella.R
import com.example.novella.databinding.FragmentNotesBinding
import com.example.novella.presentation.fragments.viewModels.BookFragmentViewModel
import com.example.novella.presentation.fragments.viewModels.NotesFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesFragment : Fragment() {

    lateinit var binding: FragmentNotesBinding
    private val viewModel by viewModel<NotesFragmentViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_notes, container, false)
        binding.vm = viewModel

        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.setNotes()

        binding.addNoteButton.setOnClickListener {
            Log.e("ABOBA","Dwqdq")
        }
    }


}