package uio.androidbootcamp.moviesapp.presenter

import android.content.Intent
import uio.androidbootcamp.moviesapp.utils.Constants
import uio.androidbootcamp.moviesapp.model.models.Movie
import java.util.*

class MovieDetailPresenter(val view: MovieDetailView) {

    fun getMovie(intent: Intent) {
        val movie = intent.getSerializableExtra(Constants.MOVIE) as Movie
        view.showMovieTitle(movie.name)
        view.showMovieDate(movie.releaseDate)
        view.showMovieDescription(movie.overview)
        view.loadImage(movie.posterPath)
    }

    interface MovieDetailView {
        fun showMovieTitle(title: String)
        fun showMovieDate(date: Date)
        fun showMovieDescription(description: String)
        fun loadImage(path: String)
    }
}