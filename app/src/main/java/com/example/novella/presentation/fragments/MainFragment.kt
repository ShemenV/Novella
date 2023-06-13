package com.example.novella.presentation.fragments

import android.R
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.novella.databinding.FragmentMainBinding
import com.example.novella.databinding.ViewPageBookItemBinding
import com.example.novella.domain.Entities.Book
import com.example.novella.presentation.MAIN
import com.example.novella.presentation.adapters.BookAdapter
import com.example.novella.presentation.adapters.MainViewPagerAdapter
import com.example.novella.presentation.adapters.OnViewPagerItemClickListener
import com.example.novella.presentation.fragments.viewModels.MainFragmentViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs


class MainFragment() : Fragment(), OnViewPagerItemClickListener {
    lateinit var binding:FragmentMainBinding
    private val viewModel by viewModel<MainFragmentViewModel>()
    private lateinit var adapter:MainViewPagerAdapter
    private lateinit var listenr: OnViewPagerItemClickListener

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, com.example.novella.R.layout.fragment_main,container,false)
        binding.vm = viewModel
        binding.viewPager2.offscreenPageLimit = 3

        listenr = this@MainFragment

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager2.apply {
            clipChildren = false  // No clipping the left and right items
            clipToPadding = false  // Show the viewpager in full width without clipping the padding
            offscreenPageLimit = 3  // Render the left and right items
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect
        }

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer{page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.8f + r*0.2f)
        }
        binding.viewPager2.setPageTransformer(compositePageTransformer)

        adapter = MainViewPagerAdapter(listenr)
        binding.viewPager2.adapter = adapter



        lifecycleScope.launch {
            viewModel.getReadBooks()
            viewModel.readNowBooksList.observe(viewLifecycleOwner, Observer {
                adapter.data = it
                adapter.notifyDataSetChanged()
            })
        }
    }

    override fun onItemClick(position: Int) {
        val selectedBook = adapter.data[position]
        if(selectedBook != null){
            val action = MainFragmentDirections.actionMainFragmentToBookFragment(selectedBook)
            MAIN.navController.navigate(action)
        }
    }
}