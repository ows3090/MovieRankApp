package ows.kotlinstudy.movierankapp.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ows.kotlinstudy.movierankapp.NetworkStatus
import ows.kotlinstudy.movierankapp.response.MovieListResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
){
    @Inject lateinit var networkStatus: NetworkStatus

    suspend fun requestMovieList(type: Int) : ResponseResult<MovieListResponse>{
        if(networkStatus == NetworkStatus.NOT_CONNECTED){
            return requestLocalDataSource(type)
        }
        return requestRemoteDataSource(type)
    }

    suspend fun requestRemoteDataSource(type: Int) : ResponseResult<MovieListResponse>{
        val response = remoteDataSource.fecthMovieList(type)

        if(response.isSuccessful){
            localDataSource.insertMovieList(response.body()?.result)
            return ResponseResult.Success(response.body(), 1)
        }
        return ResponseResult.Fail(response.body(), 2)
    }

    suspend fun requestLocalDataSource(type: Int) : ResponseResult<MovieListResponse>{
        return localDataSource.fecthMovieList(type)
    }
}