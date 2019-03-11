package uio.androidbootcamp.moviesapp.view.adapters

import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.view.SimpleDraweeView
import uio.androidbootcamp.moviesapp.R
import uio.androidbootcamp.moviesapp.model.models.Movie
class MovieListAdapter(private val moviesList: List<Movie>, private val onItemSelected: View.OnClickListener) :
        RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_item, parent, false)
        val imageView = view.findViewById(R.id.movie_list_image) as SimpleDraweeView
        imageView.setOnClickListener(onItemSelected)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById(R.id.movie_list_image) as SimpleDraweeView

    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageURI("""https://image.tmdb.org/t/p/w200${moviesList[position].posterPath}""")
        holder.imageView.tag = moviesList[position].id
    }
}