package com.example.aiktcnewapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiktcnewapp.network.News
import com.example.aiktcnewapp.network.NewsApi
import com.example.aiktcnewapp.network.LatestNews
import kotlinx.coroutines.launch

enum class NewsApiStatus { LOADING, ERROR, DONE}

class NewsViewModel: ViewModel() {


    private val _newsData = MutableLiveData<List<News>>()
    val newsData: LiveData<List<News>> = _newsData

    private val _latestnews = MutableLiveData<List<LatestNews>>()
    val latestnews: LiveData<List<LatestNews>> = _latestnews

    private val _status = MutableLiveData<NewsApiStatus>()
    val status: LiveData<NewsApiStatus> = _status

    init {
        getNews()
    }

    private fun getNews(){
        _status.value = NewsApiStatus.LOADING
        viewModelScope.launch {
            try{
                _status.value = NewsApiStatus.DONE
                _newsData.value = NewsApi.retrofitServive.getNews()
                _latestnews.value = NewsApi.retrofitServive.getLatestNews()
            } catch (e: Exception){
                _status.value = NewsApiStatus.ERROR
                Log.d("Zaki",e.toString())
                _newsData.value = listOf()
                _latestnews.value = listOf()
            }
        }
    }


}


