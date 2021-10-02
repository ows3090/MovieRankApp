package ows.kotlinstudy.movierankapp.repository

import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ows.kotlinstudy.movierankapp.database.MovieDatabase
import ows.kotlinstudy.movierankapp.response.MovieListResponse
import ows.kotlinstudy.movierankapp.response.SimpleMovie
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val database: RoomDatabase
) {
    suspend fun fecthMovieList(type: Int) : ResponseResult<MovieListResponse>{
        return withContext(Dispatchers.IO){
            if(database is MovieDatabase){
                val response = MovieListResponse(
                    result = database.movieDao().getAll()
                )
                ResponseResult.Success(response, 1)
            }else{
                ResponseResult.Fail(null, 2)
            }
        }
    }

    suspend fun insertMovieList(items : List<SimpleMovie>?){
        return withContext(Dispatchers.IO){
            if(database is MovieDatabase){
                items?.let {
                    database.movieDao().insertAllMovie(it)
                }
            }
        }
    }

}