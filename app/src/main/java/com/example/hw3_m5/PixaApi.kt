package com.example.hw3_m5

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")
    fun getImages(
        @Query("key") key: String = "43230180-a193bd89d6c3668acfd808350",
        @Query("q") keyWord: String,
        @Query("per_page")perPage:Int = 4,
        @Query("page")page:Int=1
    ): Call<PixaModel>
}