package uio.androidbootcamp.moviesapp.presenter

import android.content.Context
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uio.androidbootcamp.moviesapp.R
import uio.androidbootcamp.moviesapp.model.models.Movie
import uio.androidbootcamp.moviesapp.model.services.MovieService
import uio.androidbootcamp.moviesapp.model.services.RetrofitBuilder

class MovieListPresenter(val view: MovieListView) {

    private val movieService = RetrofitBuilder.getRetrofitInstance().create(MovieService::class.java)

    private var page = 0

    var movies = mutableListOf<Movie>()
        private set

    fun viewDidLoad() {
        view.configureRecyclerView()
    }

    fun loadMovies() {
        val context = view.getContext()
        val apiKey = context.getString(R.string.movie_database_key_id)
        page += 1
        val options = mapOf("api_key" to apiKey, "page" to page.toString())
        movieService.findPopularMovies(options).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { movieWrapper ->
                            movies.addAll(movieWrapper.results)
                            view.updateRecyclerView()
                        },
                        { error ->
                            error.printStackTrace()
                            view.showMovieRetrieveError()
                        })
    }

    fun onItemSelected(): View.OnClickListener {
        return View.OnClickListener { v ->
            val id = v?.tag as Long
            val movie = movies.firstOrNull { it.id == id }
            movie?.let { view.showMovieInformation(it) }
        }
    }

    interface MovieListView {
        fun getContext(): Context
        fun updateRecyclerView()
        fun showMovieRetrieveError()
        fun configureRecyclerView()
        fun showMovieInformation(movie: Movie)
    }
}