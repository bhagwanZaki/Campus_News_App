package com.example.aiktcnewapp.network

data class News(
    val id: Int,
    val title: String,
    val description: String,
    val image: String
)

data class LatestNews(
    val id: Int,
    val title: String,
    val description: String,
    val image: String
)