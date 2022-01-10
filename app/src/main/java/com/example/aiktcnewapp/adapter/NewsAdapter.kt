package com.example.aiktcnewapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aiktcnewapp.databinding.NewsItemBinding
import com.example.aiktcnewapp.network.News
import com.example.aiktcnewapp.ui.HomeScreenDirections

class NewsAdapter: ListAdapter<News,NewsAdapter.NewsViewHolder>(DiffCallback) {

    class NewsViewHolder(private var binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root){
        val cardView: CardView = binding.newCard
        fun bind(News:News){
            binding.newData = News
            binding.executePendingBindings()
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<News>(){

        // checks whether 2 items are same or not
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        // checks whether 2 items have same data or not
        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(NewsItemBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = getItem(position)
        holder.bind(currentNews)
        holder.cardView.setOnClickListener {
            val action = HomeScreenDirections.actionHomeScreenToNewsDetail(
                id=currentNews.id,
                title = currentNews.title,
                description = currentNews.description,
                image = currentNews.image
            )

            holder.cardView.findNavController().navigate(action)
        }
    }
}