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
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.novella.R
import com.example.novella.databinding.FragmentNotesBinding
import com.example.novella.domain.Entities.Note
import com.example.novella.presentation.MAIN
import com.example.novella.presentation.adapters.NoteAdapter
import com.example.novella.presentation.adapters.OnNoteRecyclerViewClickListener
import com.example.novella.presentation.fragments.viewModels.BookFragmentViewModel
import com.example.novella.presentation.fragments.viewModels.NotesFragmentViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesFragment : Fragment(), OnNoteRecyclerViewClickListener {

    lateinit var binding: FragmentNotesBinding
    private val viewModel by viewModel<NotesFragmentViewModel>()
    val noteAdapter: NoteAdapter by lazy { NoteAdapter(requireContext(), listener) }
    lateinit var listener: OnNoteRecyclerViewClickListener
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_notes, container, false)
        binding.vm = viewModel

        listener = this

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.setNotes()

        binding.addNoteButton.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToAddNoteFragment(null)
            MAIN.navController.navigate(action)
        }


        lifecycleScope.launch {
            viewModel.notesListMutable.observe(viewLifecycleOwner, Observer {
                noteAdapter.data = it
            })
        }


        binding.recyclerView.adapter = noteAdapter
    }

    override fun onItemClick(note: Note) {
        val action = NotesFragmentDirections.actionNotesFragmentToAddNoteFragment(note)
        MAIN.navController.navigate(action)
    }


}