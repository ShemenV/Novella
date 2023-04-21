package com.example.novella.Presentation.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.novella.Data.Room.Repository.BookRepositoryImpl
import com.example.novella.R
import com.example.novella.databinding.FragmentMainBinding

class MainFragment() : Fragment() {

    lateinit var binding:FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}