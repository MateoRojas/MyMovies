package uio.androidbootcamp.moviesapp.view.activities

import android.os.Bundle
import uio.androidbootcamp.moviesapp.R

class MovieDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_movie_detail)
        super.onCreate(savedInstanceState)
    }

    override fun getImplementingTypeClassName(): String {
        return MovieDetailActivity::class.java.simpleName
    }

    override fun isDrawerEnabled(): Boolean {
        return false
    }
}