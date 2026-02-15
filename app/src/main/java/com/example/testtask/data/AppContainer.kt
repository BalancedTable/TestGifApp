package com.example.testtask.data

import com.example.testtask.network.GifApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val GifRepository: TestGifRepository
}

class DefaultAppContainer: AppContainer{
    private val baseURL = "https://api.giphy.com/"// вставить ссылку на гифки

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseURL)
        .build()

    private val retrofitService: GifApiService by lazy {
        retrofit.create(GifApiService::class.java)
    }

    override val GifRepository: TestGifRepository by lazy {
        NetworkGifRepository(retrofitService)
    }
}