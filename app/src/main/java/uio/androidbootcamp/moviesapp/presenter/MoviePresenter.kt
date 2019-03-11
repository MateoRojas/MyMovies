package uio.androidbootcamp.moviesapp.presenter

import uio.androidbootcamp.moviesapp.model.models.Movie
import uio.androidbootcamp.moviesapp.model.services.MovieService

//Aquí se maneja la lógica de la aplicación
class MoviePresenter(val view: View) : MovieService.MoviePresenterOutput {


    private lateinit var movieService: MovieService

    fun findMovieByName(name: String) {
        movieService.findMovieByName(name)
    }

    override fun showMovieInformation(movie: Movie?) {
        if (movie == null) {
            view.showMovieNotFoundMessage()
        } else {
            view.showMovieInformation(movie)
        }
    }

    fun viewLoaded() {
        movieService = view.getMovieService()
        view.setActionsToScreenElements()
    }

    interface View {
        fun setActionsToScreenElements()
        fun showMovieInformation(movie: Movie)
        fun showMovieNotFoundMessage()
        fun getMovieService(): MovieService
    }
}