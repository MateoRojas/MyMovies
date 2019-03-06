package uio.androidbootcamp.moviesapp.model.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Movie(val id: Long = 0,
                 val title: String,
                 @SerializedName("poster_path") val posterPath: String,
                 val overview: String,
                 @SerializedName("release_date") val releaseDate: Date = Date()) : Serializable