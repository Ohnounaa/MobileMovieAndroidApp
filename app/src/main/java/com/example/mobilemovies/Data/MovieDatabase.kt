package com.example.mobilemovies.API


import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.mobilemovies.Data.Movie

@Database(entities = [Movie:: class], version = 1, exportSchema = false)
//class MovieDatabase {
    abstract class MovieDatabase: RoomDatabase() {
        //abstract val movieDAO: MovieDAO
        companion object {
            @Volatile
            private var INSTANCE: MovieDatabase? = null
            fun getInstance(context: Context): MovieDatabase? {
                synchronized(this) {
                    var instance = INSTANCE
                    if(instance == null) {
                        instance = Room.databaseBuilder(context.applicationContext,
                            MovieDatabase::class.java,
                            "movie_database").fallbackToDestructiveMigration().build()
                        INSTANCE = instance
                    }
                    return instance
                }
            }
        }
    }
//}
