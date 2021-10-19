package com.yohannes.dev.app.codenews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yohannes.dev.app.codenews.adapter.NewsAdapter
import com.yohannes.dev.app.codenews.databinding.ActivityMainBinding
import com.yohannes.dev.app.codenews.ui.NewsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityMainBinding
    private var adapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        val newsAdapter = NewsAdapter()
        //viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        val viewModel: NewsViewModel by lazy {
            ViewModelProvider(this).get(NewsViewModel::class.java)
        }

        binding.recylerView.apply {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL ,false)
            setHasFixedSize(true)
            adapter = newsAdapter
        }

        lifecycleScope.launch {
            viewModel.news.collectLatest { news ->
                adapter?.submitData(news)
            }
        }

    }
}