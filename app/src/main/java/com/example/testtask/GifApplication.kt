package com.example.testtask

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.decode.GifDecoder
import com.example.testtask.data.AppContainer
import com.example.testtask.data.DefaultAppContainer

class GifApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
        Coil.setImageLoader(createGifCapableImageLoader())
    }

    private fun createGifCapableImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(GifDecoder.Factory())
            }
            .build()
    }
}