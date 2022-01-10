package com.example.aiktcnewapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aiktcnewapp.databinding.LatestNewsItemBinding
import com.example.aiktcnewapp.network.LatestNews
import com.example.aiktcnewapp.ui.HomeScreenDirections

class LatestNewsAdapter: ListAdapter<LatestNews,LatestNewsAdapter.LatestNewsViewHolder>(DiffCallback) {

    class LatestNewsViewHolder(private var binding: LatestNewsItemBinding): RecyclerView.ViewHolder(binding.root){
        val cardView: CardView = binding.latestCard
        fun bind(latestNews:LatestNews){
            binding.latestNewsData = latestNews
            binding.executePendingBindings()
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<LatestNews>(){

        // checks whether 2 items are same or not
        override fun areItemsTheSame(oldItem: LatestNews, newItem: LatestNews): Boolean {
            return oldItem.id == newItem.id
        }

        // checks whether 2 items have same data or not
        override fun areContentsTheSame(oldItem: LatestNews, newItem: LatestNews): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestNewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LatestNewsViewHolder(LatestNewsItemBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: LatestNewsViewHolder, position: Int) {
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