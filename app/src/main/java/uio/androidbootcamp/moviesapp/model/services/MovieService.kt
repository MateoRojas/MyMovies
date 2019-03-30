package uio.androidbootcamp.moviesapp.model.services

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import uio.androidbootcamp.moviesapp.model.models.Movie
import uio.androidbootcamp.moviesapp.model.models.MovieWrapper

//Manejo de Servicios Web
class MovieService(
    private val presenterOutput: MoviePresenterOutput,
    private val movieRestService: MovieRestServices
) {
    fun findMovieByName(name: String) {
        val options = mapOf("api_key" to "api_key_to_replace", "query" to name)
        val call = movieRestService.findMoviesByName(options)
        call.enqueue(manageFindMovieResponse())
    }

    private fun manageFindMovieResponse(): Callback<MovieWrapper> {
        return object : Callback<MovieWrapper> {
            override fun onResponse(call: Call<MovieWrapper>, response: Response<MovieWrapper>) {
                response.body()?.let {
                    if (it.results.isNullOrEmpty()) {
                        presenterOutput.showMovieInformation(null)
                    }
                    presenterOutput.showMovieInformation(it.results[0])
                }
            }

            override fun onFailure(call: Call<MovieWrapper>, error: Throwable) {
                error.printStackTrace()
                presenterOutput.showMovieInformation(null)
            }
        }
    }

    interface MovieRestServices {
        @GET("movie/")
        fun findMoviesByName(@QueryMap options: Map<String, String>): Call<MovieWrapper>
    }

}

interface MoviePresenterOutput {
    fun showMovieInformation(movie: Movie?)

}
