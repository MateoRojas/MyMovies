package uio.androidbootcamp.moviesapp.model.services

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


object RetrofitBuilder {

    private var retrofit: Retrofit? = null

    private const val BASE_URL = "http://api.themoviedb.org/3/"

    fun getRetrofitInstance(): Retrofit {
        //Este bloque es para tener logs de las llamadas web
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        if (retrofit == null) {
            retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
        }
        return retrofit!!
    }
}