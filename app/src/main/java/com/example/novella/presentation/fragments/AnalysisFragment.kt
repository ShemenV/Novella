package com.example.novella.presentation.fragments

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.novella.R
import com.example.novella.databinding.FragmentAnalysisBinding
import com.example.novella.domain.Entities.BookPage
import com.example.novella.presentation.fragments.viewModels.AnalysisFragmentViewModel
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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

        list.add(PieEntry(100f, "Прочитано"))
        list.add(PieEntry(30f, "Хочу прочитать"))
        list.add(PieEntry(30f, "Читаю сейчас"))


        viewModel.totalBooksCount.observe(viewLifecycleOwner, Observer {
            binding.totalBooksCountTextView.text = it.toString()
        })


        viewModel.readedBooksCount.observe(viewLifecycleOwner, Observer {
            list[0] = PieEntry(it.toFloat(), "")
        })

        viewModel.wantReadBooksCount.observe(viewLifecycleOwner, Observer {
            list[1] = PieEntry(it.toFloat(), "")
        })

        viewModel.readNowBooksCount.observe(viewLifecycleOwner, Observer {
            list[2] = PieEntry(it.toFloat(), "")
        })


        val bookPieDataSet = PieDataSet(list, "")

        bookPieDataSet.setColors(
            intArrayOf(
                Color.parseColor("#CF3A6A"),
                Color.parseColor("#FFEFBB54"),
                Color.parseColor("#FF51F876")
            ), 255
        )
        bookPieDataSet.valueTextSize = 18f
        bookPieDataSet.valueTextColor = Color.BLACK
        bookPieDataSet.label = ""
        binding.booksCountPieChart.data = PieData(bookPieDataSet)
        binding.booksCountPieChart.animateY(500)
        binding.booksCountPieChart.animateX(300)
        binding.booksCountPieChart.centerText = "Количество книг"
        binding.booksCountPieChart.legend.orientation = Legend.LegendOrientation.VERTICAL
        binding.booksCountPieChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        binding.booksCountPieChart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        binding.booksCountPieChart.legend.setDrawInside(false)
        binding.booksCountPieChart.legend.textSize = 15f



        viewModel.totalPagesCount.observe(viewLifecycleOwner, Observer {
            binding.totalPageCountTextView.text = it.toString()
        })

        viewModel.booksList.observe(viewLifecycleOwner, Observer {

            val bookPagesList: ArrayList<PieEntry> = ArrayList()

            for(i in it){
                bookPagesList.add(PieEntry(i?.readedPages!!.toFloat(), i.title ))
            }


            val pagesPieDataSet = PieDataSet(bookPagesList, "")

            pagesPieDataSet.setColors(ColorTemplate.COLORFUL_COLORS, 255)
            pagesPieDataSet.valueTextSize = 18f
            pagesPieDataSet.valueTextColor = Color.BLACK
            pagesPieDataSet.label = ""
            binding.pageCountPieChart.data = PieData(pagesPieDataSet)
            binding.pageCountPieChart.animateY(500)
            binding.pageCountPieChart.animateX(300)
            binding.pageCountPieChart.setEntryLabelTextSize(0f)

            binding.pageCountPieChart.centerText = "Прочитано страниц"
            binding.pageCountPieChart.legend.orientation = Legend.LegendOrientation.VERTICAL
            binding.pageCountPieChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            binding.pageCountPieChart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            binding.pageCountPieChart.legend.setDrawInside(false)
            binding.pageCountPieChart.legend.textSize = 15f

            binding.pageCountPieChart.extraBottomOffset = -50f
        })

        binding.createAnalysisPdf.setOnClickListener {
            if(requireActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_DENIED
            ){
                val permission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permission, STORAGE_CODE )
            }
            else{
                viewModel.generatePdf()
            }
        }

    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
       when(requestCode){
           STORAGE_CODE -> {
               if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    viewModel.generatePdf()
               }else{
                   Toast.makeText(requireContext(), "No permissions", Toast.LENGTH_SHORT).show()
               }
           }
       }

    }

    companion object {
        private const val  STORAGE_CODE = 1001
    }

}