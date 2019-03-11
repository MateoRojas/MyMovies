package uio.androidbootcamp.moviesapp.view.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import uio.androidbootcamp.moviesapp.R
import uio.androidbootcamp.moviesapp.model.models.Movie
import uio.androidbootcamp.moviesapp.model.services.MovieListService
import uio.androidbootcamp.moviesapp.model.services.MovieRestServices
import uio.androidbootcamp.moviesapp.model.services.RetrofitInstance
import uio.androidbootcamp.moviesapp.presenter.MovieListPresenter
import uio.androidbootcamp.moviesapp.utils.Constants
import uio.androidbootcamp.moviesapp.view.adapters.MovieListAdapter

class MovieListActivity : AppCompatActivity(), MovieListPresenter.MovieListView {

    private val presenter = MovieListPresenter(this)

    private lateinit var recyclerViewMovies: RecyclerView
    private lateinit var moviesAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_movie_list)
        super.onCreate(savedInstanceState)
        presenter.viewLoaded()
    }

    override fun configureRecyclerView() {
        moviesAdapter = MovieListAdapter(presenter.movies, presenter.onItemSelected())
        recyclerViewMovies = findViewById(R.id.recycler_view_movies)
        recyclerViewMovies.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = moviesAdapter
        }
        presenter.loadMovies()
    }

    override fun updateRecyclerView() {
        moviesAdapter.notifyDataSetChanged()
    }

    override fun showMovieRetrieveError() {
        Toast.makeText(this, getString(R.string.movies_cannot_be_retrieved), Toast.LENGTH_LONG).show()
    }

    override fun getMovieListService(): MovieListService {
        val movieRestService : MovieRestServices = RetrofitInstance.retrofit.create(MovieRestServices::class.java)
        return MovieListService(presenter, movieRestService)
    }

    override fun showMovieInformation(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(Constants.MOVIE, movie)
        startActivity(intent)
    }


}