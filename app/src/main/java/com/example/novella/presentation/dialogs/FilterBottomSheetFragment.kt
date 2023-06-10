package com.example.novella.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.novella.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomSheetFragment(val listener: FilterDialogListener):BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.filter_bottom_sheet_dialog,container,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val titleSortButton = view.findViewById<RadioButton>(R.id.titleSortButton)
        val pageCountSortButton = view.findViewById<RadioButton>(R.id.pageCountSortButton)

        val changeListener = (object :OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                var resultType:String = ""
                if(buttonView!!.id == R.id.titleSortButton && isChecked){
                    resultType = "TitleSort"
                }
                if(buttonView!!.id ==  R.id.pageCountSortButton && isChecked){
                    resultType = "PageCountSort"
                }

                listener.sortTypeChanged(resultType)
            }

        })
        titleSortButton.setOnCheckedChangeListener(changeListener)
        pageCountSortButton.setOnCheckedChangeListener(changeListener)


        val descendingButton = view.findViewById<RadioButton>(R.id.descendingButton)
        val ascendingButton = view.findViewById<RadioButton>(R.id.ascendingButton)

        val orderListener = (object :OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                var resultType:String = ""
                if(buttonView!!.id == R.id.ascendingButton && isChecked){
                    resultType = "Descending"
                }
                if(buttonView!!.id ==  R.id.descendingButton && isChecked){
                    resultType = "Ascending"
                }

                listener.sortOrderChanged(resultType)
            }

        })
        descendingButton.setOnCheckedChangeListener(orderListener)
        ascendingButton.setOnCheckedChangeListener(orderListener)
    }

    companion object {
        const val TAG = "FilterBottomSheet"
    }
}

interface FilterDialogListener{
    fun sortTypeChanged(type:String)
    fun sortOrderChanged(order:String)
}