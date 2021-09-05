package ows.kotlinstudy.movierankapp.repository

import androidx.room.RoomDatabase
import ows.kotlinstudy.movierankapp.database.MovieDatabase
import ows.kotlinstudy.movierankapp.response.MovieListResponse
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val database: RoomDatabase
) {
    suspend fun fecthMovieList(type: Int) : ResponseResult<MovieListResponse>{
        if(database is MovieDatabase){
            val response = MovieListResponse(
                result = database.movieDao().getAll()
            )
            return ResponseResult.Success(response, 1)
        }
        return ResponseResult.Fail(null, 2)
    }
}