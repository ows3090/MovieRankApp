package ows.kotlinstudy.movierankapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ows.kotlinstudy.movierankapp.response.Comment

@Dao
interface CommentDao {

    @Query("SELECT * FROM Comment WHERE movieId = :id")
    fun getAll(id : Int) : List<Comment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllComment(items : List<Comment>)
}