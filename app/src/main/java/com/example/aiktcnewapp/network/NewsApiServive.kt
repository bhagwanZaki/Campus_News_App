package com.example.aiktcnewapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

private const val  BASE_URL = "https://aiktc-news.herokuapp.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val httpClient = OkHttpClient.Builder()
    .callTimeout(2,TimeUnit.MINUTES)
    .connectTimeout(1,TimeUnit.MINUTES)
    .readTimeout(1,TimeUnit.MINUTES)
    .writeTimeout(30,TimeUnit.SECONDS)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(httpClient)
    .baseUrl(BASE_URL)
    .build()

object NewsApi{
    val retrofitServive : NewsApiServive by lazy {
        retrofit.create(NewsApiServive::class.java)
    }
}

interface NewsApiServive{
    @GET("api/news")
    suspend fun getNews(): List<News>

    @GET("api/latestnews")
    suspend fun getLatestNews(): List<LatestNews>
}