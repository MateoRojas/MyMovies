package uio.androidbootcamp.moviesapp.view.activities

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_movie_detail.*
import uio.androidbootcamp.moviesapp.R
import uio.androidbootcamp.moviesapp.presenter.MovieDetailPresenter
import uio.androidbootcamp.moviesapp.presenter.MovieDetailView
import java.util.*

class MovieDetailActivity : BaseActivity(), MovieDetailView {

    private val presenter: MovieDetailPresenter = MovieDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_movie_detail)
        super.onCreate(savedInstanceState)
        presenter.getMovie(intent)
    }

    override fun getImplementingTypeClassName(): String {
        return MovieDetailActivity::class.java.simpleName
    }

    override fun isDrawerEnabled(): Boolean {
        return false
    }

    override fun loadImage(path: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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