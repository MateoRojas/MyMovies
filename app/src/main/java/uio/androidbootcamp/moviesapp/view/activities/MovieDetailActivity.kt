package uio.androidbootcamp.moviesapp.view.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*
import uio.androidbootcamp.moviesapp.R
import uio.androidbootcamp.moviesapp.presenter.MovieDetailPresenter
import java.util.*

class MovieDetailActivity : AppCompatActivity(), MovieDetailPresenter.MovieDetailView {

    private val presenter: MovieDetailPresenter = MovieDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_movie_detail)
        super.onCreate(savedInstanceState)
        presenter.getMovie(intent)
    }

    override fun loadImage(path: String) {
        image_view_movie_poster.setImageURI(path)
    }

    override fun showMovieTitle(title: String) {
        text_view_movie_title.text = title
    }

    override fun showMovieDate(date: Date) {
        text_view_movie_release_date.text = date.toString()
    }

    override fun showMovieDescription(description: String) {
        text_view_movie_description.text = description
    }
}
