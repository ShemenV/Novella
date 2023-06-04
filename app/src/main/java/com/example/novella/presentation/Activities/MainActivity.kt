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