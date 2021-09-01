package ows.kotlinstudy.movierankapp.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ows.kotlinstudy.movierankapp.response.MovieListResponse
import retrofit2.Response

class MovieRepository {
    suspend fun requestMovieList(type: Int) : Response<MovieListResponse>{
        return withContext(Dispatchers.IO){
            ApiRequestFactory.retrofit.requestMovieListForCorotine(type)
        }
    }
}