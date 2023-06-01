package com.example.novella.presentation.fragments

import android.os.Bundle
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
import com.example.novella.R
import com.example.novella.databinding.FragmentBookBinding
import com.example.novella.databinding.FragmentLibraryBinding
import com.example.novella.presentation.MAIN
import com.example.novella.presentation.adapters.BRVAHAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class LibraryFragment : Fragment() {

    lateinit var binding: FragmentLibraryBinding
    private val vm by viewModel<LibraryFragmentViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_library, container, false)

        binding.vm = vm

        binding.addBoookButton.setOnClickListener {
            MAIN.navController.navigate(R.id.action_libraryFragment_to_addBookFragment)
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter :BRVAHAdapter = BRVAHAdapter(mutableListOf())
        val manager: LayoutManager = GridLayoutManager(activity?.applicationContext,2)


        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = manager

        adapter.setOnItemClickListener { _, view, position ->
            val selectedBook = adapter.getItem(position)
            if(selectedBook != null){
                val action = LibraryFragmentDirections.actionLibraryFragmentToBookFragment(selectedBook)

                MAIN.navController.navigate(action)
            }

        }

        lifecycleScope.launch{
            vm.getAllBooks()
            vm.readBookList.observe(viewLifecycleOwner, Observer { books ->
                if(books != null){
                    adapter.setNewInstance(books)
                }
            })


            vm.search.observe(viewLifecycleOwner, Observer {
                if (it != null ) {

                    vm.filterBooks(it)
                }
            })
        }
    }
}