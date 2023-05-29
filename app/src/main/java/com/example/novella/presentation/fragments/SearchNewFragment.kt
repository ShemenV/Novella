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
import com.example.novella.presentation.adapters.BookAdapter
import com.example.novella.presentation.fragments.viewModels.SearchNewFragmentViewModel
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



        lifecycleScope.launch {
            viewModel.booksList.observe(viewLifecycleOwner, Observer{ books ->
               adapter.data = books
        })
        }

        binding.searchButton.setOnClickListener {
            viewModel.getBooksByName(binding.searchEditText.text.toString())
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = manager


        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager: GridLayoutManager = GridLayoutManager::class.java.cast(recyclerView.layoutManager) as GridLayoutManager
                val totalItemCount:Int = layoutManager.itemCount
                val lastVisiblePosition: Int = layoutManager.findLastVisibleItemPosition()

                val isEnded: Boolean = lastVisiblePosition + 5 >= totalItemCount

                if(totalItemCount >0 && isEnded){
                    viewModel.updateBooksByStartIndex(totalItemCount)
                }

            }
        })
    }
}