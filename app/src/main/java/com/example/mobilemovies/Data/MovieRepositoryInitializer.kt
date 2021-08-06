package com.example.mobilemovies.API

import android.app.Application

class MovieRepositoryInitializer: Application() {
    override fun onCreate() {
        super.onCreate()
        MovieRepository.initialize(this)
    }
}