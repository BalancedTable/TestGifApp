package com.example.testtask.network

data class GiphyResponse(
    val data: List<GifDto>
)

data class GifDto(
    val id: String,
    val images: Images
)

data class Images(
    val original: Original
)

data class Original(
    val url: String
)