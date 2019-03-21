package uio.androidbootcamp.moviesapp.view.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_find_movie.*
import uio.androidbootcamp.moviesapp.utils.Constants
import uio.androidbootcamp.moviesapp.R
import uio.androidbootcamp.moviesapp.model.models.Movie
import uio.androidbootcamp.moviesapp.presenter.MoviePresenter

// Mostly show methods!!!! Really dumb methods!
// Probably a load for an image
class FindMovieActivity : AppCompatActivity(), MoviePresenter.View {

    private val presenter = MoviePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_movie)
        presenter.viewLoaded()
    }

    override fun setActionsToScreenElements() {
        button_find_movie.setOnClickListener {
            presenter.findMovieByName(edit_text_movie_name.text.toString())
        }
    }

    override fun showMovieNotFound() {
        val text = getString(R.string.movie_not_found)
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    override fun showMovieInformation(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(Constants.MOVIE, movie)
        startActivity(intent)
    }
}
