package com.dicoding.asclepius.data.api

import com.dicoding.asclepius.database.NewsDao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val newsApiService: NewsDao = retrofit.create(NewsDao::class.java)
}