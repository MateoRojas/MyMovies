package uio.androidbootcamp.moviesapp.presenter

import uio.androidbootcamp.moviesapp.model.models.Movie
import uio.androidbootcamp.moviesapp.model.services.MoviePresenterOutput
import uio.androidbootcamp.moviesapp.model.services.MovieService

//Aquí se maneja la lógica de la aplicación
class MoviePresenter(val view: View): MoviePresenterOutput {
    private val movieService = MovieService(this)

    fun viewLoaded() {
        view.setActionsToScreenElements()
    }

    fun findMovieByName(name: String) {
        movieService.findMovieByName(name)
    }

    override fun showMovieInformation(movie: Movie?) {
        view.showMovieInformation(movie)
    }
}

interface View {
    fun showMovieInformation(movie: Movie?)
    fun setActionsToScreenElements()
}