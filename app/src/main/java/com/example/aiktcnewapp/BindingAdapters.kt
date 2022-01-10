package com.example.aiktcnewapp

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.aiktcnewapp.adapter.LatestNewsAdapter
import com.example.aiktcnewapp.adapter.NewsAdapter
import com.example.aiktcnewapp.network.News
import com.example.aiktcnewapp.network.LatestNews
import com.example.aiktcnewapp.ui.NewsApiStatus

@BindingAdapter("listLatestNewsData")
fun bindLatestNewsRecyclerView(recyclerView: RecyclerView, data: List<LatestNews>?){
    Log.d("Zaki","in ad")
    val adapter = recyclerView.adapter as LatestNewsAdapter
    adapter.submitList(data)
}

@BindingAdapter("listNewsData")
fun bindNewsRecyclerView(recyclerView: RecyclerView, data: List<News>?){
    Log.d("Zaki","in ad")
    val adapter = recyclerView.adapter as NewsAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl:String?){
    imageUrl?.let{
        val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
        imageView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("newsApiStatus")
fun bindStatus(statusImageView: ImageView, status: NewsApiStatus?){
    Log.d("Zaki",status.toString())
    when(status){
        NewsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        NewsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        NewsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}