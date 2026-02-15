package com.example.testtask.network

import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.giphy.com/"//ресурс откуда брать гифки

interface GifApiService{
    @GET("v1/gifs/trending")//вставить что буду вставлять гифки тобишь

    suspend fun getGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int
    ): GiphyResponse
}