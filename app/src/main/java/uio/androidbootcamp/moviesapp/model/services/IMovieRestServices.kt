package uio.androidbootcamp.moviesapp.model.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap
import uio.androidbootcamp.moviesapp.model.models.MovieWrapper

interface IMovieRestServices {

    @GET("search/movie/")
    fun findMoviesByName(@QueryMap options: Map<String, String>): Call<MovieWrapper>

    @GET("movie/popular")
    fun findPopularMovies(@QueryMap options: Map<String, String>): Call<MovieWrapper>
}