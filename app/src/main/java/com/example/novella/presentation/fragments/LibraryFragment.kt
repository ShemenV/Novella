package com.example.novella.presentation.fragments

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
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
import com.example.novella.presentation.adapters.BookItem
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

        var mUndoHelper: UndoHelper<*>
        var mActionModeHelper: ActionModeHelper<BookItem>
        var selectExtension: SelectExtension<BookItem>
        val itemAdapter :ItemAdapter<BookItem> = ItemAdapter<BookItem>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        selectExtension = fastAdapter.getSelectExtension()
        selectExtension.apply {
            isSelectable = true
            multiSelect = true
            selectOnLongClick = true
            selectionListener = object : ISelectionListener<BookItem> {
                override fun onSelectionChanged(item: BookItem, selected: Boolean) {
                    Log.i("FastAdapter", "SelectedCount: " + selectExtension.selections.size + " ItemsCount: " + selectExtension.selectedItems.size)
                }
            }
        }



        fastAdapter.onClickListener = { v: View?, _: IAdapter<BookItem>, _:BookItem, _: Int ->
            if (v != null) {
                Toast.makeText(
                    v.context,
                    "SelectedCount: " + selectExtension.selections.size + " ItemsCount: " + selectExtension.selectedItems.size,
                    Toast.LENGTH_SHORT
                ).show()
            }
            false
        }





        val manager: LayoutManager = GridLayoutManager(activity?.applicationContext,2)
        binding.recyclerView.adapter = fastAdapter
        binding.recyclerView.layoutManager = manager

        fastAdapter.onClickListener = {view, adapter, item, position ->
            val selectedBook = adapter.getAdapterItem(position).book
            if(selectedBook != null){
                val action = LibraryFragmentDirections.actionLibraryFragmentToBookFragment(selectedBook)
                MAIN.navController.navigate(action)
            }

            false
        }

        fastAdapter.onLongClickListener = {view, adapter, item, position ->



            false
        }

        lifecycleScope.launch{
            vm.getAllBooks()
            vm.readBookList.observe(viewLifecycleOwner, Observer { books ->
                if(books != null){
                    Log.e("__","observe")
                    itemAdapter.set(books.map(::BookItem))
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