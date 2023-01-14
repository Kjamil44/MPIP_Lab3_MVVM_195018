package mk.ukim.finki.lab3_195018.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mk.ukim.finki.lab3_195018.models.Data

@Database(entities = [Data::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private var dbInstance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (dbInstance == null) {
                dbInstance = createInstance(context)
            }
            return dbInstance!!
        }

        private fun createInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "movielist.db"
            ).build()
        }
    }
}