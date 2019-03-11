package uio.androidbootcamp.moviesapp.view.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_find_movie.*
import uio.androidbootcamp.moviesapp.R
import uio.androidbootcamp.moviesapp.model.models.Movie
import uio.androidbootcamp.moviesapp.model.services.MovieRestServices
import uio.androidbootcamp.moviesapp.model.services.MovieService
import uio.androidbootcamp.moviesapp.model.services.RetrofitInstance
import uio.androidbootcamp.moviesapp.presenter.MoviePresenter
import uio.androidbootcamp.moviesapp.utils.Constants.MOVIE


class FindMovieActivity : AppCompatActivity(), MoviePresenter.View {

    private val  presenter = MoviePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_movie)
        presenter.viewLoaded()
    }

    override fun showMovieInformation(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE, movie)
        startActivity(intent)
    }

    override fun getMovieService(): MovieService {
        val movieRestService : MovieRestServices = RetrofitInstance.retrofit.create(MovieRestServices::class.java)
        return MovieService(presenter, movieRestService)
    }


    override fun showMovieNotFoundMessage() {
        Toast.makeText(this, getString(R.string.movie_not_found), Toast.LENGTH_LONG).show()
    }

    override fun setActionsToScreenElements() {
        button_find_movie.setOnClickListener {
            presenter.findMovieByName(edit_text_movie_name.text.toString())
        }
    }
}
