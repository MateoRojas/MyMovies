package uio.androidbootcamp.moviesapp.model.services

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object RetrofitInstance {
    private const val BASE_URL = "http://api.themoviedb.org/3/search/"

    val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}