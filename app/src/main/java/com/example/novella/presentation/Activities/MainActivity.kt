package com.example.novella.presentation.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.novella.presentation.MAIN
import com.example.novella.R
import com.example.novella.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet(val status:Int = 1) : BottomSheetDialogFragment() {

     lateinit var wantReadRadioButton: RadioButton
     lateinit var readedRadioButton: RadioButton
     lateinit var readNowRadioButton: RadioButton

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
            2 -> wantReadRadioButton.isChecked = true
            3 -> readedRadioButton.isChecked = true
            4 -> readNowRadioButton.isChecked = true
        }



    }
    companion object {
        const val TAG = "ModalBottomSheet"
    }

    fun getSelectedStatus(): Int{
        if( wantReadRadioButton.isChecked){
            return 2
        }
        else if(readedRadioButton.isChecked){
            return 3
        }

        else if(readNowRadioButton.isChecked){
            return 4
        }
        else{
            return 1
        }
    }
}
class MainActivity(): AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        MAIN = this



    }
}