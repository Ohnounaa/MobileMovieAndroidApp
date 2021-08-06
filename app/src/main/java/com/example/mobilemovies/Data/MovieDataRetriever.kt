package com.example.mobilemovies.API

import Repository.MovieAPI
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mobilemovies.Data.Movie
import com.example.mobilemovies.Data.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDataRetriever {
    fun retrieveMostPopularMoviesData(pageNumber:String) : Movies {
        val url = "https://api.themoviedb.org/3/"
        var popularMovies: Movies = Movies(0, arrayListOf(),0,0)
        val retrofit: Retrofit = Retrofit.
        Builder().
        baseUrl(url).
        addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: MovieAPI = retrofit.create(MovieAPI::class.java)
        val retriever = api.retrieveMostPopularMovies(pageNumber)
        retriever.enqueue(object: Callback<Movies> {
            override fun onResponse(
                call: Call<Movies>,
                response: Response<Movies>
            ) {
                response.isSuccessful.let {
                    val resp = Movies(response.body()?.total_pages?:0,
                        response.body()?.results?: mutableListOf(),
                        response.body()?.total_pages?:0,
                        response.body()?.total_results?:0
                    )
                    popularMovies = resp
                }
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("Please try again.", t.message.toString())
            }
        })
        return popularMovies
    }


    fun retrieveNowPlayingMoviesData(pageNumber:String) : MutableLiveData<List<Movie>> {
        var currentMovies: MutableLiveData<List<Movie>>  = MutableLiveData()
        val retrofit: Retrofit = Retrofit.
        Builder().
        baseUrl("https://api.themoviedb.org/3/").
        addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: MovieAPI = retrofit.create(MovieAPI::class.java)
        val retriever = api.retrieveNowPlayingMovies()
        retriever.enqueue(object: Callback<Movies> {
            override fun onResponse(
                call: Call<Movies>,
                response: Response<Movies>
            ) {
                response.isSuccessful.let {
                    val resp = Movies(response.body()?.page?:0,
                        response.body()?.results?: mutableListOf(),
                        response.body()?.total_pages?:0,
                        response.body()?.total_results?:0)
                    currentMovies.value = resp.results
                }
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("OHNOUNA", t.message.toString())
            }
        })
            return currentMovies
    }
}