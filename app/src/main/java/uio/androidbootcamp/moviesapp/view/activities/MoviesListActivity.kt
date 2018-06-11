package uio.androidbootcamp.moviesapp.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import uio.androidbootcamp.moviesapp.R
import uio.androidbootcamp.moviesapp.model.models.Movie
import uio.androidbootcamp.moviesapp.presenter.MovieListPresenter
import uio.androidbootcamp.moviesapp.utils.Constants
import uio.androidbootcamp.moviesapp.utils.toast
import uio.androidbootcamp.moviesapp.view.adapters.EndlessRecyclerOnScrollListener
import uio.androidbootcamp.moviesapp.view.adapters.MovieListAdapter

class MoviesListActivity : BaseActivity(), MovieListPresenter.MovieListView {
    private val presenter = MovieListPresenter(this)

    private lateinit var recyclerViewMovies: RecyclerView
    private lateinit var moviesAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_movies_list)
        super.onCreate(savedInstanceState)
        setActionBarTitle(getString(R.string.list_movies))
        presenter.viewDidLoad()
    }

    override fun isDrawerEnabled(): Boolean = true

    override fun getImplementingTypeClassName(): String {
        return MoviesListActivity::class.java.simpleName
    }

    override fun getContext(): Context {
        return this
    }

    override fun showMovieInformation(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(Constants.MOVIE, movie)
        startActivity(intent)
    }

    override fun configureRecyclerView() {
        moviesAdapter = MovieListAdapter(presenter.movies, presenter.onItemSelected())
        recyclerViewMovies = findViewById(R.id.recycler_view_movies)
        recyclerViewMovies.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = moviesAdapter
            addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
                override fun onLoadMore() {
                    presenter.loadMovies()
                }
            }
            )
        }
        presenter.loadMovies()
    }

    override fun updateRecyclerView() {
        moviesAdapter.notifyDataSetChanged()
    }

    override fun showMovieRetrieveError() {
        toast(getString(R.string.movies_cannot_be_retrieved))
    }

}
