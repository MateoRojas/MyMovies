package uio.androidbootcamp.moviesapp.presenter

import uio.androidbootcamp.moviesapp.model.models.Movie
import uio.androidbootcamp.moviesapp.model.services.MoviePresenterOutput
import uio.androidbootcamp.moviesapp.model.services.MovieService

//Aquí se maneja la lógica de la aplicación
class MoviePresenter(val movieView: MovieView): MoviePresenterOutput {

    private lateinit var movieService: MovieService

    fun viewLoaded() {
        movieService = movieView.getMovieService()
        movieView.setActionsToScreenElements()
    }

    fun findMovieByName(name: String) {
        movieService.findMovieByName(name)
    }

    override fun showMovieInformation(movie: Movie?) {
        movie?.let { movieView.showMovieInformation(movie) } ?: movieView.showMovieNotFound()
    }

    interface MovieView {
        fun showMovieInformation(movie: Movie)
        fun showMovieNotFound()
        fun setActionsToScreenElements()
        fun getMovieService(): MovieService
    }
}