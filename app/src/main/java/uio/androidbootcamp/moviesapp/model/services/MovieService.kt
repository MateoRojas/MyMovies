package uio.androidbootcamp.moviesapp.model.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.Callback
import retrofit2.Response
import uio.androidbootcamp.moviesapp.model.models.Movie


//Manejo de Servicios Web
class MovieService(private val presenterOutput: MoviePresenterOutput, private val movieRestService: MovieRestServices) {

    fun findMovieByName(name: String) {
        val options = mapOf("api_key" to "api_key_to_replace", "query" to name)
        val call = movieRestService.findMovieByName(options)
        println(call.request())
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

    interface MoviePresenterOutput {
        fun showMovieInformation(movie: Movie?)
    }

    interface MovieRestServices {
        @GET("movie/")
        fun findMovieByName(@QueryMap options: Map<String, String>): Call<MovieWrapper>
    }

    data class MovieWrapper(val results: List<Movie>)
}