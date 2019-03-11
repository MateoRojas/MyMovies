package uio.androidbootcamp.moviesapp.presenter

import android.view.View
import uio.androidbootcamp.moviesapp.model.models.Movie
import uio.androidbootcamp.moviesapp.model.services.MovieListService

class MovieListPresenter(val view: MovieListView) : MovieListService.MovieListPresenterOutput {

    private lateinit var movieListService: MovieListService
    var movies = mutableListOf<Movie>()
        private set

    fun viewLoaded() {
        movieListService = view.getMovieListService()
        view.configureRecyclerView()
    }

    fun loadMovies() {
        movieListService.findMostPopularMovies()
    }

    fun onItemSelected(): View.OnClickListener {
        return View.OnClickListener { v ->
            val id = v?.tag as Long
            val movie = movies.firstOrNull { it.id == id }
            movie?.let { view.showMovieInformation(it) }
        }
    }

    override fun showMovies(movies: List<Movie>?) {
        when (movies) {
            null -> view.showMovieRetrieveError()
            else -> {
                this.movies.addAll(movies)
                view.updateRecyclerView()
            }
        }
    }

    interface MovieListView {
        fun updateRecyclerView()
        fun showMovieRetrieveError()
        fun getMovieListService(): MovieListService
        fun showMovieInformation(movie: Movie)
        fun configureRecyclerView()
    }
}