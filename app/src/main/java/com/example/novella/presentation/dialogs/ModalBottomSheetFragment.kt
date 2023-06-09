package com.example.novella.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.novella.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheetFragment(val listener: ModalBottomListener, val status:Int = 1) : BottomSheetDialogFragment() {

    private var chooseReadStatusListener: ModalBottomListener? = null

    lateinit var wantReadRadioButton: RadioButton
    lateinit var readedRadioButton: RadioButton
    lateinit var readNowRadioButton: RadioButton


    init {
        chooseReadStatusListener = listener
    }

    override fun getTheme(): Int  = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.book_status_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wantReadRadioButton = view.findViewById(R.id.wantReadRadio)
        readedRadioButton = view.findViewById(R.id.readedRadio)
        readNowRadioButton = view.findViewById(R.id.readNowRadio)

        val selectedStatus: Int = status

        when(selectedStatus){
            1 -> {
                wantReadRadioButton.isChecked = false
                readedRadioButton.isChecked = false
                readNowRadioButton.isChecked = false
            }
            4 -> wantReadRadioButton.isChecked = true
            3 -> readedRadioButton.isChecked = true
            2 -> readNowRadioButton.isChecked = true
        }

        wantReadRadioButton.setOnCheckedChangeListener { compoundButton, b ->
            if(compoundButton.isChecked)
                chooseReadStatusListener?.chooseReadStatusClick(4)
        }

        readedRadioButton.setOnCheckedChangeListener { compoundButton, b ->
            if(compoundButton.isChecked)
                chooseReadStatusListener?.chooseReadStatusClick(3)
        }

        readNowRadioButton.setOnCheckedChangeListener { compoundButton, b ->
            if(compoundButton.isChecked)
                chooseReadStatusListener?.chooseReadStatusClick(2)
        }



    }
    companion object {
        const val TAG = "ModalBottomSheet"
    }

    fun getSelectedStatus(): Int{
        if( wantReadRadioButton.isChecked){
            return 4
        }
        else if(readedRadioButton.isChecked){
            return 3
        }

        else if(readNowRadioButton.isChecked){
            return 2
        }
        else{
            return 1
        }
    }


    interface ModalBottomListener{
        fun chooseReadStatusClick(seletedStatus:Int)
    }
}