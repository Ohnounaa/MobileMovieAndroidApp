package com.example.mobilemovies.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilemovies.API.MovieRepository
import com.example.mobilemovies.Data.Movie
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = MovieRepository.retrieve()
    var currentMovies : MutableLiveData<List<Movie>>? = null

    init{
        getNowPlayingMovies("1")
    }

    fun getNowPlayingMovies(pageNumber: String)  {
        viewModelScope.launch {
            val moviesAPIData = repository.getNowPlayingMoviesFromAPI("1")
            currentMovies = moviesAPIData
        }
    }
}