package com.example.novella.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.novella.R
import com.example.novella.databinding.FragmentAnalysisBinding
import com.example.novella.presentation.fragments.viewModels.AnalysisFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnalysisFragment : Fragment() {

    private lateinit var binding: FragmentAnalysisBinding
    val viewModel by viewModel<AnalysisFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_analysis, container, false)
        binding.vm = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.setBooksCount()

        viewModel.totalBooksCount.observe(viewLifecycleOwner, Observer {
            binding.totalBooksCountTextView.text = it.toString()
        })

    }

}