package com.example.hw3_m5

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val retrofit = Retrofit.Builder().baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    val api = retrofit.create(PixaApi::class.java)
}