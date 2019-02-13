package uio.androidbootcamp.moviesapp.presenter

import android.content.Intent
import uio.androidbootcamp.moviesapp.model.models.Movie
import uio.androidbootcamp.moviesapp.utils.Constants.MOVIE
import java.util.*

class MovieDetailPresenter(val view: MovieDetailView) {

    fun getMovie(intent: Intent) {
        val movie: Movie = intent.getSerializableExtra(MOVIE) as Movie
        view.loadImage(movie.posterPath)
        view.showMovieTitle(movie.name)
        view.showMovieDate(movie.releaseDate)
        view.showMovieDescription(movie.overview)
    }

    interface MovieDetailView {
        fun loadImage(path: String)
        fun showMovieTitle(title: String)
        fun showMovieDate(date: Date)
        fun showMovieDescription(description: String)
    }
}