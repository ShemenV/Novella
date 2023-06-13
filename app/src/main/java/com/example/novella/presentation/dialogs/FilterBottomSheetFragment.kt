package com.example.novella.presentation.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import android.widget.Filter
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.novella.R
import com.example.novella.domain.Entities.Filters
import com.example.novella.domain.Entities.SortOrders
import com.example.novella.domain.Entities.SortParams
import com.example.novella.domain.Entities.SortTypes
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*
import kotlin.collections.ArrayList

class FilterBottomSheetFragment(val listener: FilterDialogListener, private val sortParams: SortParams):BottomSheetDialogFragment() {

    private var filtersList: MutableList<Int> = sortParams.filtersList.toMutableList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.filter_bottom_sheet_dialog,container,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val titleSortButton = view.findViewById<RadioButton>(R.id.titleSortButton)
        val pageCountSortButton = view.findViewById<RadioButton>(R.id.pageCountSortButton)
        val descendingButton = view.findViewById<RadioButton>(R.id.descendingButton)
        val ascendingButton = view.findViewById<RadioButton>(R.id.ascendingButton)
        val readedCheckBox = view.findViewById<CheckBox>(R.id.readedCheckBox)
        val wantReadCheckBox = view.findViewById<CheckBox>(R.id.wantReadCheckBox)
        val readNowCheckBox = view.findViewById<CheckBox>(R.id.readNowCheckBox)

        fun setupView(){
            when(sortParams.sortType){
                SortTypes.TitleSort -> {
                    titleSortButton.isChecked = true
                    pageCountSortButton.isChecked = false
                }
                SortTypes.PageCountSort ->{
                    titleSortButton.isChecked = false
                    pageCountSortButton.isChecked = true
                }
                else -> {}
            }


            when(sortParams.sortOrder){
                SortOrders.Ascending -> {
                    ascendingButton.isChecked = true
                    descendingButton.isChecked = false
                }
                SortOrders.Descending ->{
                    ascendingButton.isChecked = false
                    descendingButton.isChecked = true
                }
                else -> {}
            }

            wantReadCheckBox.isChecked = sortParams.filtersList.contains(4)
            readNowCheckBox.isChecked = sortParams.filtersList.contains(2)
            readedCheckBox.isChecked = sortParams.filtersList.contains(3)
        }



        val changeListener = (object :OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                var resultType:SortTypes = SortTypes.None
                if(buttonView!!.id == R.id.titleSortButton && isChecked){
                    resultType = SortTypes.TitleSort
                }
                if(buttonView!!.id ==  R.id.pageCountSortButton && isChecked){
                    resultType = SortTypes.PageCountSort
                }

                listener.sortTypeChanged(resultType)
            }

        })
        titleSortButton.setOnCheckedChangeListener(changeListener)
        pageCountSortButton.setOnCheckedChangeListener(changeListener)




        val orderListener = (object :OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                var resultType:SortOrders = SortOrders.None
                if(buttonView!!.id == R.id.ascendingButton && isChecked){
                    resultType = SortOrders.Ascending
                }
                if(buttonView!!.id ==  R.id.descendingButton && isChecked){
                    resultType = SortOrders.Descending
                }

                listener.sortOrderChanged(resultType)
            }

        })
        descendingButton.setOnCheckedChangeListener(orderListener)
        ascendingButton.setOnCheckedChangeListener(orderListener)
        setupView()


        readNowCheckBox.setOnCheckedChangeListener { button, checked ->
            if(checked){
                filtersList.add(2)
            }
            else{
                filtersList.remove(2)
            }
            Log.e("aaa",filtersList.toString())
            listener.filtersChanged(filtersList)
        }

        readedCheckBox.setOnCheckedChangeListener { button, checked ->
            if(checked){
                filtersList.add(3)
            }
            else{
                filtersList.remove(3)
            }
            Log.e("aaa",filtersList.toString())
            listener.filtersChanged(filtersList)
        }

        wantReadCheckBox.setOnCheckedChangeListener { button, checked ->
            if(checked){
                filtersList.add(4)
            }
            else{
                filtersList.remove(4)
            }
            Log.e("aaa",filtersList.toString())
            listener.filtersChanged(filtersList)
        }





        val saveButton = view.findViewById<Button>(R.id.saveFiltersButton)
        saveButton.setOnClickListener {
            this@FilterBottomSheetFragment.dismiss()
            listener.resultSort()
        }


    }


    companion object {
        const val TAG = "FilterBottomSheet"
    }
}

interface FilterDialogListener{
    fun sortTypeChanged(type:SortTypes)
    fun sortOrderChanged(order: SortOrders)
    fun filtersChanged(filters: List<Int>)
    fun resultSort()
}