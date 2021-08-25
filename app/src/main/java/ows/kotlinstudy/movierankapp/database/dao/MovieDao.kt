package ows.kotlinstudy.movierankapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ows.kotlinstudy.movierankapp.data.SimpleMovie

@Dao
interface MovieDao {
    @Query("SELECT * FROM SimpleMovie")
    fun getAll() : List<SimpleMovie>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(simpleMovie: SimpleMovie)

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovie(items : List<SimpleMovie>)
}