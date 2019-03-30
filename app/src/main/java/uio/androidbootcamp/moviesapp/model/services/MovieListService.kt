package uio.androidbootcamp.moviesapp.model.services

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uio.androidbootcamp.moviesapp.model.models.Movie
import uio.androidbootcamp.moviesapp.model.models.MovieWrapper

class MovieListService(
    private val presenterOutput: MovieListPresenterOutput,
    private val movieRestServices: IMovieRestServices
) {
    fun findMostPopularMovies() {
        val options = mapOf("api_key" to "api_key_to_replace")
        val call = movieRestServices.findPopularMovies(options)
        call.enqueue(manageFindPopularMoviesResponse())
    }

    private fun manageFindPopularMoviesResponse(): Callback<MovieWrapper> {
        return object : Callback<MovieWrapper> {
            override fun onFailure(call: Call<MovieWrapper>, error: Throwable) {
                error.printStackTrace()
                presenterOutput.showMovies(null)
            }

            override fun onResponse(call: Call<MovieWrapper>, response: Response<MovieWrapper>) {
                response.body()?.let {
                    if (it.results.isNullOrEmpty()) {
                        presenterOutput.showMovies(null)
                    }
                    presenterOutput.showMovies(it.results)
                }
            }
        }
    }

    interface MovieListPresenterOutput {
        fun showMovies(movies: List<Movie>?)
    }
}