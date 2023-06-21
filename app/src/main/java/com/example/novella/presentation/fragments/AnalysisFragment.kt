package com.example.novella.presentation.fragments

import android.graphics.Color
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
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
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
        viewModel.setBooksList()
        viewModel.setTotalPagesCount()


        val list: ArrayList<PieEntry> = ArrayList()

        list.add(PieEntry(100f, "Всего книг"))
        list.add(PieEntry(30f, "Всцуацуа"))
        list.add(PieEntry(30f, "уцацуа"))


        viewModel.totalBooksCount.observe(viewLifecycleOwner, Observer {
            binding.totalBooksCountTextView.text = it.toString()
        })


        viewModel.readedBooksCount.observe(viewLifecycleOwner, Observer {
            list[0] = PieEntry(it.toFloat(), "Прочитано")
        })

        viewModel.wantReadBooksCount.observe(viewLifecycleOwner, Observer {
            list[1] = PieEntry(it.toFloat(), "Хочу прочитать")
        })

        viewModel.readNowBooksCount.observe(viewLifecycleOwner, Observer {
            list[2] = PieEntry(it.toFloat(), "Читаю сейчас")
        })


        val pieDataSet = PieDataSet(list, "Количество книг")

        pieDataSet.setColors(
            intArrayOf(
                Color.parseColor("#CF3A6A"),
                Color.parseColor("#FFEFBB54"),
                Color.parseColor("#FF51F876")
            ), 255
        )
        pieDataSet.valueTextSize = 20f
        pieDataSet.valueTextColor = Color.BLACK
        binding.booksCountPieChart.data = PieData(pieDataSet)
        binding.booksCountPieChart.animateY(500)
        binding.booksCountPieChart.animateX(300)
        binding.booksCountPieChart.centerText = "Количество книг"



        viewModel.totalPagesCount.observe(viewLifecycleOwner, Observer {
            binding.totalPageCountTextView.text = it.toString()
        })


    }
}