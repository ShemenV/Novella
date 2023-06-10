package com.example.novella.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.novella.presentation.fragments.viewModels.LibraryFragmentViewModel
import com.example.novella.R
import com.example.novella.databinding.FragmentLibraryBinding
import com.example.novella.domain.Entities.Book
import com.example.novella.presentation.MAIN
import com.example.novella.presentation.adapters.BookAdapter
import com.example.novella.presentation.adapters.OnRecyclerViewItemClickListener
import com.example.novella.presentation.dialogs.FilterBottomSheetFragment
import com.example.novella.presentation.dialogs.FilterDialogListener
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class LibraryFragment : Fragment(),
    OnRecyclerViewItemClickListener,
        FilterDialogListener
{

    lateinit var binding: FragmentLibraryBinding
    private val vm by viewModel<LibraryFragmentViewModel>()
    lateinit var onRecyclerViewItemClickListener: OnRecyclerViewItemClickListener
    lateinit var filterDialogListener: FilterDialogListener
    lateinit var adapter: BookAdapter

    val args: LibraryFragmentArgs by navArgs()
    val changed: Boolean by lazy { args.dataChanged}

    init {
        onRecyclerViewItemClickListener = this
        filterDialogListener = this
    }
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


        Log.e("Library","Create")
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BookAdapter(requireContext(),onRecyclerViewItemClickListener)
        Log.e("Library","Created")
        val manager: LayoutManager = GridLayoutManager(activity?.applicationContext,2)
        binding.recyclerView.adapter =adapter
        binding.recyclerView.layoutManager = manager

        lifecycleScope.launch{
            vm.getAllBooks()
            vm.readBookList.observe(viewLifecycleOwner, Observer { books ->
                if(books != null && !adapter.data.equals(books)){
                    adapter.data = books
                    adapter.notifyDataSetChanged()
                }
            })


            binding.filterButton.setOnClickListener {
                val filterBottomSheetDialog = FilterBottomSheetFragment(filterDialogListener)
                filterBottomSheetDialog.show(childFragmentManager,FilterBottomSheetFragment.TAG)
            }

            binding.librarySearchView.setOnQueryTextListener(object : OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        vm.filterBooks(query)
                    }
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    if(query.isNullOrEmpty()){
                        vm.getAllBooks()
                    }
                    return true
                }

            })

            vm.search.observe(viewLifecycleOwner, Observer {
                if (it != null ) {
                    vm.filterBooks(it)
                }
            })
        }
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onItemClick(position: Int) {
        val selectedBook = adapter.data[position]
            if(selectedBook != null){
                val action = LibraryFragmentDirections.actionLibraryFragmentToBookFragment(selectedBook)
                MAIN.navController.navigate(action)
            }
    }

    override fun menuItem1Click(book: Book) {
        Log.e("Context Menu", "Delete book - ${book.title}")

       val dialog= AlertDialog.Builder(requireContext())
            .setMessage("Вы действительно хотите удалить ${book.title}")
            .setPositiveButton("Да") { _,_ ->
                vm.deleteBook(book)
            }
            .setNegativeButton("Нет"){_,_,->}
            .create()

        dialog.show()
//        lifecycleScope.launch{
//            vm.deleteBook(book)
//        }

    }


    override fun menuItem2Click(book: Book) {
        Log.e("Context Menu", "Edit book - ${book.title}")
        val action = LibraryFragmentDirections.actionLibraryFragmentToEditBookFragment(book)
        MAIN.navController.navigate(action)
    }


    override fun sortTypeChanged(value: String) {
        if(value != "" ){
            Log.e("SortType",value.toString())
            vm.selectedSortType = value
        }
    }

    override fun sortOrderChanged(order: String) {
        if(order != "" ){
            Log.e("SortOrder",order.toString())
            vm.selectedSortOrder = order
        }
    }


}