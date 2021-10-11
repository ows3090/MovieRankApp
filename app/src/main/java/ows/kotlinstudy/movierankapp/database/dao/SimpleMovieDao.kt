package ows.kotlinstudy.movierankapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ows.kotlinstudy.movierankapp.repository.model.SimpleMovie

@Dao
interface SimpleMovieDao {
    @Query("SELECT * FROM SimpleMovie")
    fun getAll() : List<SimpleMovie>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertSimpleMovie(simpleMovie: SimpleMovie)

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSimpleMovie(items : List<SimpleMovie>)
}