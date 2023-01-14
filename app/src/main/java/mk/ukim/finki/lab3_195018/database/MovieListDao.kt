package mk.ukim.finki.lab3_195018.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mk.ukim.finki.lab3_195018.models.Data

@Dao
abstract class MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDatas(data: MutableList<Data>)

    @Query("SELECT * FROM Data WHERE title = :title")
    abstract fun getMovies(title: String): List<Data>
}