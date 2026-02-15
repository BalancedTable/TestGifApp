package com.example.testtask.network

import retrofit2.http.GET
import retrofit2.http.Query

interface GifApiService{
    @GET("v1/gifs/trending")

    suspend fun getGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int
    ): GiphyResponse
}