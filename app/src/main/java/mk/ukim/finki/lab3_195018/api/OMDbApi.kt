package mk.ukim.finki.lab3_195018.api

import mk.ukim.finki.lab3_195018.models.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDbApi {

    @GET(".")
    fun getMoviesByTitle(@Query("apikey") apiKey: String,
                         @Query("page") page: String,
                         @Query("type") type: String,
                         @Query("s") s: String): Call<MovieList>
}