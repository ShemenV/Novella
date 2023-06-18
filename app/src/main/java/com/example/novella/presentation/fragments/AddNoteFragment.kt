package com.example.novella.presentation.fragments

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.novella.R
import com.example.novella.databinding.FragmentAddNoteBinding
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.Entities.Note
import com.example.novella.presentation.adapters.BookSpinnerAdapter
import com.example.novella.presentation.fragments.viewModels.AddNoteFragmentViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNoteFragment : Fragment() {

    lateinit var binding: FragmentAddNoteBinding
    val viewModel by viewModel<AddNoteFragmentViewModel>()
    val args: AddNoteFragmentArgs by navArgs()
    val selectNote: Note? by lazy { args.selectedNote }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_add_note, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

            Log.e("selectedNote", selectNote.toString())
            viewModel.initLiveData()



        val adapter = BookSpinnerAdapter(requireContext())

        viewModel.booksListMutable.observe(viewLifecycleOwner, Observer {
            Log.e("f", it.toString())
            adapter.data = it
            binding.selectNoteBookSpinner.setSelection(adapter.data.indexOf(selectNote?.book))
            adapter.notifyDataSetChanged()
        })

        binding.selectNoteBookSpinner.adapter = adapter
        binding.addTitleTextInputLayout.editText?.setText(selectNote?.title)
        binding.addTextTextInputLayout.editText?.setText(selectNote?.text)
        viewModel.setSelctedNote(selectNote!!)












        binding.selectNoteBookSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.addingNoteMutable.value?.book = adapter.data[position]
            }
        }



        binding.addTextTextInputLayout.editText?.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               viewModel.addingNoteMutable.value?.text = p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.addTitleTextInputLayout.editText?.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.addingNoteMutable.value?.title = p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.saveNoteButton.setOnClickListener {
            Log.e("SAVED-NOTE",viewModel.addingNoteMutable.value.toString())
            viewModel.saveNote()
        }
    }

}