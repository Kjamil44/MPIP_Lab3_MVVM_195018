package mk.ukim.finki.lab3_195018.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mk.ukim.finki.lab3_195018.database.AppDatabase
import mk.ukim.finki.lab3_195018.models.Data

class SecondFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val database: AppDatabase = AppDatabase.getInstance(application)
    private var movieLiveData: MutableLiveData<Data> = MutableLiveData()

    fun findMovieByTitle(title: String) {
        CoroutineScope(Dispatchers.IO).launch {
            movieLiveData.postValue(database.movieDao().getMovies(title)[0])
        }
    }
    fun getMovieMutableLiveData(): MutableLiveData<Data>{
        return movieLiveData;
    }
}