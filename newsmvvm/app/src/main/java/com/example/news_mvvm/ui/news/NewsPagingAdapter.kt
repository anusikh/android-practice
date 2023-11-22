package com.example.news_mvvm.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news_mvvm.BR
import com.example.news_mvvm.data.Article
import com.example.news_mvvm.databinding.ViewHolderNewsBinding

class NewsPagingAdapter : PagingDataAdapter<Article, NewsPagingAdapter.MyViewHolder>(DIFF_UTIL) {

    inner class MyViewHolder(val viewDataBinding: ViewHolderNewsBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)

        holder.viewDataBinding.setVariable(BR.article, data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding =
            ViewHolderNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

}