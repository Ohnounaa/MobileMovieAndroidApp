package com.example.mobilemovies.Data

import androidx.room.Entity

@Entity
data class Movies(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)