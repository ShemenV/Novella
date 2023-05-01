package com.example.novella.Presentation.Fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.novella.Presentation.Fragments.ViewModels.MainFragmentViewModel
import com.example.novella.Presentation.adapters.BookAdapter
import com.example.novella.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment() : Fragment() {
    lateinit var binding:FragmentMainBinding
    private val vm by viewModel<MainFragmentViewModel>()

    private lateinit var adapter: BookAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)


        val manager = LinearLayoutManager(activity?.applicationContext)
        adapter = BookAdapter()

        lifecycleScope.launch {
            vm.getReadBooks()
            vm.readBooksList.observe(viewLifecycleOwner, Observer{ books ->
                adapter.data = books
                Log.e("===========",books.get(0)?.cover?.get(0).toString())
            })



            binding.recyclerView.layoutManager = manager
            binding.recyclerView.adapter = adapter
            Log.e("fragment",vm.readBooksList.toString())
        }





        return binding.root
    }
}