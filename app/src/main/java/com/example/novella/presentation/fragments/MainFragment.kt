package com.example.novella.presentation.fragments

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
import com.example.novella.presentation.fragments.viewModels.MainFragmentViewModel
import com.example.novella.presentation.MAIN
import com.example.novella.presentation.adapters.BookAdapter
import com.example.novella.R
import com.example.novella.databinding.FragmentMainBinding
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
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager = LinearLayoutManager(activity?.applicationContext)
        adapter = BookAdapter()

        lifecycleScope.launch {
            vm.getReadBooks()
            vm.readBooksList.observe(viewLifecycleOwner, Observer{ books ->
                adapter.data = books
                Log.e("===========",books.get(0)?.cover?.get(0).toString())
            })
        binding.currentBookLayout.setOnClickListener {
            MAIN.navController.navigate(R.id.action_mainFragment_to_bookFragment)
        }



            Log.e("fragment",vm.readBooksList.toString())
        }


    }
}