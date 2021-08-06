package Repository

import com.example.mobilemovies.Data.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/popular?api_key=51c73e162cf5c4b1265e9d3e4b8f0128&language=en-US&page=1")
    fun retrieveNowPlayingMovies(): Call<Movies>

    @GET("/movie/now_playing?api_key=51c73e162cf5c4b1265e9d3e4b8f0128&language=en-US&page=1")
    fun retrieveMostPopularMovies(@Query("page")page: String): Call<Movies>

}