package com.example.novella.presentation.fragments

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.VIEW_LOG_TAG
import com.example.novella.presentation.fragments.viewModels.LibraryFragmentViewModel
import com.example.novella.R
import com.example.novella.databinding.FragmentBookBinding
import com.example.novella.databinding.FragmentLibraryBinding
import com.example.novella.domain.Entities.Book
import com.example.novella.presentation.MAIN
import com.example.novella.presentation.adapters.BRVAHAdapter
import com.example.novella.presentation.adapters.BookAdapter
import com.example.novella.presentation.adapters.BookItem
import com.example.novella.presentation.adapters.OnRecyclerViewItemClickListener
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.ISelectionListener
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.mikepenz.fastadapter.helpers.ActionModeHelper
import com.mikepenz.fastadapter.helpers.UndoHelper
import com.mikepenz.fastadapter.select.SelectExtension
import com.mikepenz.fastadapter.select.getSelectExtension
import com.mikepenz.fastadapter.select.selectExtension
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.FieldPosition


class LibraryFragment : Fragment(),
    OnRecyclerViewItemClickListener
{

    lateinit var binding: FragmentLibraryBinding
    private val vm by viewModel<LibraryFragmentViewModel>()
    lateinit var listener: OnRecyclerViewItemClickListener
    lateinit var adapter: BookAdapter

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



        listener = this

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BookAdapter(requireContext(),listener)

        val manager: LayoutManager = GridLayoutManager(activity?.applicationContext,2)
        binding.recyclerView.adapter =adapter
        binding.recyclerView.layoutManager = manager

        lifecycleScope.launch{
            vm.getAllBooks()
            vm.readBookList.observe(viewLifecycleOwner, Observer { books ->
                if(books != null && !adapter.data.equals(books)){
                    adapter.data = books
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

        lifecycleScope.launch{
            vm.deleteBook(book)

        }

    }

    override fun menuItem2Click(book: Book) {
        Log.e("Context Menu", "Edit book - ${book.title}")
        val action = LibraryFragmentDirections.actionLibraryFragmentToEditBookFragment(book)
        MAIN.navController.navigate(action)
    }


}