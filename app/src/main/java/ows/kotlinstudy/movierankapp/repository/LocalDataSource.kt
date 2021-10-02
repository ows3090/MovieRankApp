package ows.kotlinstudy.movierankapp.repository

import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ows.kotlinstudy.movierankapp.database.MovieDatabase
import ows.kotlinstudy.movierankapp.response.Movie
import ows.kotlinstudy.movierankapp.response.MovieDetailResponse
import ows.kotlinstudy.movierankapp.response.MovieListResponse
import ows.kotlinstudy.movierankapp.response.SimpleMovie
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val database: RoomDatabase
) {
    suspend fun fecthSimpleMovieList(type: Int) : ResponseResult<MovieListResponse>{
        return withContext(Dispatchers.IO){
            if(database is MovieDatabase){
                val response = MovieListResponse(
                    result = database.simpleMovieDao().getAll()
                )
                ResponseResult.Success(response, 1)
            }else{
                ResponseResult.Fail(null, 2)
            }
        }
    }

    suspend fun insertSimpleMovieList(items : List<SimpleMovie>?){
        return withContext(Dispatchers.IO){
            if(database is MovieDatabase){
                items?.let {
                    database.simpleMovieDao().insertAllSimpleMovie(it)
                }
            }
        }
    }

    suspend fun fetchMovieDetail(id : Int) : ResponseResult<MovieDetailResponse>{
        return withContext(Dispatchers.IO){
              if(database is MovieDatabase){
                  val response = MovieDetailResponse(
                      result = database.movieDao().getAll(id)
                  )
                  ResponseResult.Success(response, 1)
              }else{
                  ResponseResult.Fail(null, 2)
              }
        }
    }

    suspend fun insertMovieDetail(movie : Movie?){
        return withContext(Dispatchers.IO){
            if(database is MovieDatabase){
                movie?.let {
                    database.movieDao().insertMovie(it)
                }
            }
        }
    }
}