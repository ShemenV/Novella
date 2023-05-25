package com.example.novella.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.novella.presentation.fragments.viewModels.LibraryFragmentViewModel
import com.example.novella.presentation.adapters.BookAdapter
import com.example.novella.R
import com.example.novella.databinding.FragmentLibraryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class LibraryFragment : Fragment() {

    lateinit var binding: FragmentLibraryBinding
    private val vm by viewModel<LibraryFragmentViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_library, container, false)

        binding.viewmodel = vm


        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter: BookAdapter = BookAdapter()
        val manager: LayoutManager = GridLayoutManager(activity?.applicationContext,2)

        lifecycleScope.launch{
            vm.getAllBooks()

            vm.readBookList.observe(viewLifecycleOwner, Observer { books ->
                if(books != null){
                    adapter.data = books
                }

            })

            vm.search.observe(viewLifecycleOwner, Observer {
                if (it != null ) {

                    vm.filterBooks(it)
                }
            })
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = manager
    }


}