package com.example.news_mvvm.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_mvvm.R
import com.example.news_mvvm.databinding.FragmentNewsBinding
import com.example.news_mvvm.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
    lateinit var binding: FragmentNewsBinding
    val newsAdapter = NewsPagingAdapter()
    val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setRecylerView()

        viewModel.list.observe(viewLifecycleOwner) {
            newsAdapter.submitData(lifecycle, it)
        }
    }

    private fun setRecylerView() {
        binding.newsRecycler.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


}