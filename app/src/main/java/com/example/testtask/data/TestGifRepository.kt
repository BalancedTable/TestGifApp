package com.example.testtask.data

import com.example.testtask.network.GIF
import com.example.testtask.network.GifApiService

interface TestGifRepository{
    suspend fun getTestGif(): List<GIF>
}

class NetworkGifRepository(
    private val gifApiService: GifApiService
): TestGifRepository{
    override suspend fun getTestGif(): List<GIF> {
        val response = gifApiService.getGifs(
            apiKey = Constants.GIPHY_API_KEY,
            limit = 40
        )
        return response.data.map { dto ->
            GIF(
                id = dto.id,
                gifSrc = dto.images.original.url
            )
        }
    }
}
