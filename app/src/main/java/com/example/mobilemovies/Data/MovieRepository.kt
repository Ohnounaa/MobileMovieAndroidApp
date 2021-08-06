package com.example.mobilemovies.API

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.mobilemovies.API.MovieDatabase.Companion.getInstance
import com.example.mobilemovies.Data.Movie
import com.example.mobilemovies.Data.Movies

class MovieRepository private constructor(context: Context){

   suspend fun getNowPlayingMoviesFromAPI(pageNumber:String): MutableLiveData<List<Movie>> {
        return MovieDataRetriever().retrieveNowPlayingMoviesData(pageNumber);
    }

   // private val database: MovieDatabase? = MovieDatabase.getInstance(context.applicationContext)

 //   private val movieDao = database?.movieDAO

//TODO REMOVE DOUBLE BANG
  //  fun getNowPlayingMovies(): MutableState<List<Movie>> = movieDao!!.getMovies()

  //  fun insertWeatherInfo(dwi:DailyWeatherInfo) = weatherDao.insertWeatherDay(dwi)


    companion object{
        private var INSTANCE: MovieRepository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = MovieRepository(context)
            }
        }

        fun retrieve(): MovieRepository {
            return INSTANCE?: throw IllegalStateException("Repository has not been initialized.")
        }

    }
}