package uio.androidbootcamp.moviesapp.view.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import uio.androidbootcamp.moviesapp.R
import uio.androidbootcamp.moviesapp.R.id.action_find
import uio.androidbootcamp.moviesapp.R.id.action_list
import uio.androidbootcamp.moviesapp.utils.ActivitiesNames

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isDrawerEnabled()) {
            enableNavigationDrawer()
        }
    }

    abstract fun getImplementingTypeClassName(): String

    abstract fun isDrawerEnabled(): Boolean

    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun enableNavigationDrawer() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        setSupportActionBar(toolbar)
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_close,
                R.string.drawer_open) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }
        }

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {

                action_list -> {
                    if (getImplementingTypeClassName() != ActivitiesNames.MOVIES_LIST_ACTIVITY.className) {
                        intent = Intent(this, MoviesListActivity::class.java)
                        startActivity(intent)
                    }
                }
                action_find -> {
                    if (getImplementingTypeClassName() != ActivitiesNames.FIND_MOVIES_ACTIVITY.className) {
                        intent = Intent(this, FindMovieActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
}
