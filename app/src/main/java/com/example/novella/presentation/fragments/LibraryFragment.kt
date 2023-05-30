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
import com.example.novella.presentation.adapters.BookAdapter
import com.example.novella.R
import com.example.novella.databinding.FragmentLibraryBinding
import com.example.novella.presentation.MAIN
import com.example.novella.presentation.adapters.BookItem
import com.mikepenz.fastadapter.FastAdapter
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
    ): View? {
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

        val adapter :BookAdapter = BookAdapter(requireActivity())
        val manager: LayoutManager = GridLayoutManager(activity?.applicationContext,2)

        val itemAdapter = ItemAdapter<BookItem>()
        val fastAdapter = FastAdapter.with(itemAdapter)

        lifecycleScope.launch{
            vm.getAllBooks()

            vm.readBookList.observe(viewLifecycleOwner, Observer { books ->
                if(books != null){
                    FastAdapterDiffUtil[itemAdapter] = books.map(::BookItem)
                }

            })

            vm.search.observe(viewLifecycleOwner, Observer {
                if (it != null ) {

                    vm.filterBooks(it)
                }
            })
        }




        binding.recyclerView.adapter = fastAdapter
        binding.recyclerView.layoutManager = manager
    }


}