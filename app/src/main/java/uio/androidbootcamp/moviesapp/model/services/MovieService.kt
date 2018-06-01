package uio.androidbootcamp.moviesapp.model.services

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap
import uio.androidbootcamp.moviesapp.model.models.MovieWrapper

//Manejo de Servicios Web
interface MovieService {

    @GET("movie/")
    fun findMovieByName(@QueryMap options: Map<String, String>): Observable<MovieWrapper>
}
