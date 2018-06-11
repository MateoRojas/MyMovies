package uio.androidbootcamp.moviesapp.presenter

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uio.androidbootcamp.moviesapp.R
import uio.androidbootcamp.moviesapp.model.models.Movie
import uio.androidbootcamp.moviesapp.model.services.MovieService
import uio.androidbootcamp.moviesapp.model.services.RetrofitBuilder

//Aquí se maneja la lógica de la aplicación
class MoviePresenter(val view: View) {

    private val movieService = RetrofitBuilder.getRetrofitInstance().create(MovieService::class.java)

    fun findMovieByName(name: String) {
        val context = view.getContext()
        val apiKey = context.getString(R.string.movie_database_key_id)
        val options = mapOf("api_key" to apiKey, "query" to name)
        movieService.findMovieByName(options).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { movieWrapper ->
                            val movie = movieWrapper.results[0]
                            showMovieInformation(movie)
                        },
                        { error ->
                            error.printStackTrace()
                            showMovieInformation(null)
                        })
    }

    private fun showMovieInformation(movie: Movie?) {
        if (movie == null) {
            view.showMovieNotFoundMessage()
        } else {
            view.showMovieInformation(movie)
        }
    }

    fun viewLoaded() {
        view.setActionsToScreenElements()
    }
}

interface View {
    fun getContext(): Context
    fun setActionsToScreenElements()
    fun showMovieInformation(movie: Movie)
    fun showMovieNotFoundMessage()
}
