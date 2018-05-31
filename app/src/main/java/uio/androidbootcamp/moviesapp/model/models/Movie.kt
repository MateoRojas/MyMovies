package uio.androidbootcamp.moviesapp.model.models

import java.io.Serializable
import java.util.Date

data class Movie(val id: Long = 0,
                 val name: String,
                 val posterPath: String,
                 val overview: String,
                 val releaseDate: Date = Date()) : Serializable {

    constructor() : this(0, "", "", "", Date())

}
