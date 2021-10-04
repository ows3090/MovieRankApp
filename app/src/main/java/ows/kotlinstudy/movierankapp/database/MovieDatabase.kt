package ows.kotlinstudy.movierankapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ows.kotlinstudy.movierankapp.database.dao.CommentDao
import ows.kotlinstudy.movierankapp.database.dao.MovieDao
import ows.kotlinstudy.movierankapp.response.SimpleMovie
import ows.kotlinstudy.movierankapp.database.dao.SimpleMovieDao
import ows.kotlinstudy.movierankapp.response.Comment
import ows.kotlinstudy.movierankapp.response.Movie

@Database(entities = arrayOf(SimpleMovie::class, Movie::class, Comment::class), version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun simpleMovieDao() : SimpleMovieDao
    abstract fun movieDao() : MovieDao
    abstract fun commentDao() : CommentDao
}