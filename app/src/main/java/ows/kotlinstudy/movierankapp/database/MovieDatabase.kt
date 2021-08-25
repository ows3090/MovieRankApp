package ows.kotlinstudy.movierankapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ows.kotlinstudy.movierankapp.data.SimpleMovie
import ows.kotlinstudy.movierankapp.database.dao.MovieDao

@Database(entities = arrayOf(SimpleMovie::class), version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
}