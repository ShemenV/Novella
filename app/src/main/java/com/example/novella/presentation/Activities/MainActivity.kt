package com.example.novella.presentation.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.novella.presentation.MAIN
import com.example.novella.R
import com.example.novella.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView


class MainActivity(): AppCompatActivity(),
BottomNavigationView.OnNavigationItemSelectedListener{
    lateinit var binding:ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navMenu.setOnItemSelectedListener(this)
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        MAIN = this



    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.libraryMenuItem -> {
                MAIN.navController.navigate(R.id.libraryFragment)
            }
            R.id.mainMenuItem -> MAIN.navController.navigate(R.id.mainFragment)
            R.id.quotesMenuItem -> MAIN.navController.navigate(R.id.notesFragment)
            R.id.analysisMenuItem -> MAIN.navController.navigate(R.id.analysisFragment)
        }

    }
}