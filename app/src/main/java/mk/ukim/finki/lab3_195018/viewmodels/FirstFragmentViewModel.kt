package mk.ukim.finki.lab3_195018.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mk.ukim.finki.lab3_195018.api.OMDbApi
import mk.ukim.finki.lab3_195018.api.OMDbApiClient
import mk.ukim.finki.lab3_195018.database.AppDatabase
import mk.ukim.finki.lab3_195018.models.MovieList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragmentViewModel(application: Application): AndroidViewModel(application) {

    private var omdbApiClient: OMDbApi = OMDbApiClient.getOMDbApi()!!
    private var app: Application = application
    private var movieListMutableLiveData: MutableLiveData<MovieList> = MutableLiveData()
    private val database: AppDatabase = AppDatabase.getInstance(application)

    fun searchMoviesByTitle(title: String){
        omdbApiClient.getMoviesByTitle("23b30df8", "1", "movie", title).enqueue(object :
            Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>?, response: Response<MovieList>) {
                if (response.body() != null){
                    val movies: MovieList = response.body()
                    saveMovieListInDatabase(movies)
                    movieListMutableLiveData.postValue(movies)
                    Toast.makeText(app, "Success!", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(app, "Error", Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: Call<MovieList>?, t: Throwable) {
                Toast.makeText(app, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun saveMovieListInDatabase(currentMovieList: MovieList) {
        CoroutineScope(Dispatchers.IO).launch {
            database.movieDao().insertDatas(currentMovieList.Search)
        }
    }

    fun getMovieListMutableLiveData(): MutableLiveData<MovieList>{
        return movieListMutableLiveData;
    }
}