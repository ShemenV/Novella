package com.example.novella.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.novella.R
import com.example.novella.databinding.FragmentSearchBinding
import com.example.novella.domain.Entities.Book
import com.example.novella.presentation.MAIN
import com.example.novella.presentation.adapters.BRVAHAdapter
import com.example.novella.presentation.adapters.BookAdapter
import com.example.novella.presentation.adapters.BookItem
import com.example.novella.presentation.adapters.LoadMoreView
import com.example.novella.presentation.fragments.viewModels.SearchNewFragmentViewModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter.Companion.items
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.mikepenz.fastadapter.dsl.genericFastAdapter
import com.mikepenz.fastadapter.scroll.EndlessRecyclerOnScrollListener
import com.mikepenz.fastadapter.ui.items.ProgressItem
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.HttpException

class SearchNewFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModel<SearchNewFragmentViewModel>()
    lateinit var adapter:BRVAHAdapter
    private val customLoadMoreView = LoadMoreView()


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


        val manager: LayoutManager =GridLayoutManager(activity,2)
        adapter = BRVAHAdapter(mutableListOf())
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter

        adapter.loadMoreModule.loadMoreView = customLoadMoreView
        adapter.loadMoreModule.setOnLoadMoreListener {
            val handler = android.os.Handler()
            handler.postDelayed({ loadMore() }, 10) }

        adapter.setOnItemClickListener { _, view, position ->
            val selectedBook = adapter.getItem(position)
            if(selectedBook != null){
                val action = SearchNewFragmentDirections.actionAddBookFragmentToBookFragment2(selectedBook)
                MAIN.navController.navigate(action)
            }

        }

        lifecycleScope.launch {
            viewModel.booksList.observe(viewLifecycleOwner, Observer{ books ->
                Log.e("_____________________",books.toString())
                if(books?.isEmpty() == true){
                    Toast.makeText(activity?.applicationContext,"Проверьте соединение с сетью",Toast.LENGTH_SHORT).show()
                }
            adapter.setNewInstance(books)
        })
        }

        binding.searchButton.setOnClickListener {
            try {
            val handler = android.os.Handler()

            handler.postDelayed({  binding.recyclerView.scrollToPosition(0)
                adapter.data.clear()
                viewModel.getBooksByName(binding.searchEditText.text.toString()) }, 100)
            }
            catch (e: Exception){
                Log.e("_________________________________________________",e.toString())
            }
        }



    }


    private fun loadMore(){
        try {
            val data = viewModel.getNextPageBook()

            if (!data.isNullOrEmpty() && data.last() != adapter.data.last()) {
                adapter.addData(data)
            }
            else if(!data.isNullOrEmpty() && data.size < 10){
                adapter.addData(data)
                adapter.loadMoreModule.loadMoreEnd()

                return
            }
            adapter.loadMoreModule.isEnableLoadMore = true
            adapter.loadMoreModule.loadMoreComplete()
        }
      catch (e: Exception){
          Log.e("RetrofitHTTPexception", e.toString())
      }
    }
}