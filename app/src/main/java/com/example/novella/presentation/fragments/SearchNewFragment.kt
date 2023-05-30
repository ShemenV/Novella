package com.example.novella.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.novella.R
import com.example.novella.databinding.FragmentSearchBinding
import com.example.novella.domain.Entities.Book
import com.example.novella.presentation.adapters.BookAdapter
import com.example.novella.presentation.adapters.BookItem
import com.example.novella.presentation.fragments.viewModels.SearchNewFragmentViewModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter.Companion.items
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.mikepenz.fastadapter.scroll.EndlessRecyclerOnScrollListener
import com.mikepenz.fastadapter.ui.items.ProgressItem
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchNewFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModel<SearchNewFragmentViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_search, container, false)
        binding.vm = viewModel
        return binding.root
}
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter :BookAdapter = BookAdapter(requireActivity())
        val manager: LayoutManager = GridLayoutManager(activity?.applicationContext, 2)

        val itemAdapter = ItemAdapter<BookItem>()

        val footerAdapter = ItemAdapter<ProgressItem>()
        val fastAdapter: FastAdapter<GenericItem> = FastAdapter.with(listOf(itemAdapter, footerAdapter))


        lifecycleScope.launch {
            viewModel.booksList.observe(viewLifecycleOwner, Observer{ books ->
                FastAdapterDiffUtil[itemAdapter] = books.map(::BookItem)
        })
        }

        binding.searchButton.setOnClickListener {
            binding.recyclerView.smoothScrollToPosition(0)
            viewModel.getBooksByName(binding.searchEditText.text.toString())
        }

        binding.recyclerView.adapter = fastAdapter
        binding.recyclerView.layoutManager = manager

        binding.recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener(footerAdapter) {
            override fun onLoadMore(currentPage: Int) {
                footerAdapter.clear()
                footerAdapter.add(ProgressItem())

                val book = Book(title = "dd",
                author = "frfrf",
                id = 12,
                coverUrl = "https://i.imgur.com/UCAa0By.jpeg",
                cover = null,
                description = "dcdc",
                pageCount = 123,
                publisher = "Aboba",
                readStatus = 1)
                itemAdapter.add(listOf(book).map(::BookItem))
            }
        })

    }
}