package com.example.novella.Presentation.Fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.novella.Data.Retrofit.Interfaces.BookService
import com.example.novella.Data.Room.Repository.BookRepositoryImpl
import com.example.novella.Domain.Entities.Book
import com.example.novella.Presentation.Fragments.ViewModels.MainFragmentViewModel
import com.example.novella.Presentation.adapters.BookAdapter
import com.example.novella.R
import com.example.novella.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment() : Fragment() {
    lateinit var binding:FragmentMainBinding
    private val vm by viewModel<MainFragmentViewModel>()

    private lateinit var adapter: BookAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)


        val manager = LinearLayoutManager(activity?.applicationContext)
        adapter = BookAdapter()


        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bookApi = retrofit.create(BookService::class.java)

        lifecycleScope.launch {

            val books: List<Book?>? = bookApi.getBookList("r").items?.map { value -> value?.ToBook() }
            Log.e("++++++++++++++++++++++++", books?.get(0)?.author.toString() )
//            vm.A()
//            vm.G.observe(viewLifecycleOwner, Observer{books ->
//                adapter.data = books
//            })
//
//            binding.recyclerView.layoutManager = manager
//            binding.recyclerView.adapter = adapter
//            Log.e("fragment",vm.G.toString())
        }





        return binding.root
    }
}