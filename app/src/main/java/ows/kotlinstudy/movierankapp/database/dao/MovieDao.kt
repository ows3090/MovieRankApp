package ows.kotlinstudy.movierankapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ows.kotlinstudy.movierankapp.repository.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getAll(id : Int) : List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)
}