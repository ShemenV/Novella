package com.example.novella.Presentation.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.novella.Data.Room.Repository.BookRepositoryImpl
import com.example.novella.Domain.Entities.Book
import com.example.novella.Presentation.Fragments.ViewModels.MainFragmentViewModel
import com.example.novella.R
import com.example.novella.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment() : Fragment() {

    lateinit var binding:FragmentMainBinding

    private val vm by viewModel<MainFragmentViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)


        lifecycleScope.launch {
            vm.A()

            vm.G.observe(viewLifecycleOwner, Observer{books ->
                binding.bookNameTextView.text = books.toString()
            })
            Log.e("fragment",vm.G.toString())
        }





        return binding.root
    }
}