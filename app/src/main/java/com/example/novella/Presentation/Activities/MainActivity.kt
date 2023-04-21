package com.example.novella.Presentation.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.novella.Data.Room.AppDatabase
import com.example.novella.Data.Room.Dao.BooksDao_Impl
import com.example.novella.Data.Room.Repository.BookRepositoryImpl
import com.example.novella.Domain.Entities.Book
import com.example.novella.Presentation.MAIN
import com.example.novella.R
import com.example.novella.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

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