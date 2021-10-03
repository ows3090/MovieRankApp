package ows.kotlinstudy.movierankapp.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ows.kotlinstudy.movierankapp.NetworkStatus
import ows.kotlinstudy.movierankapp.response.MovieCommentResponse
import ows.kotlinstudy.movierankapp.response.MovieDetailResponse
import ows.kotlinstudy.movierankapp.response.MovieListResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
){
    @Inject lateinit var networkStatus: NetworkStatus

    suspend fun requestSimpleMovieList(type: Int) : ResponseResult<MovieListResponse>{
        /**
         * fetch in LocalDataSource
         */
        if(networkStatus == NetworkStatus.NOT_CONNECTED){
            return localDataSource.fecthSimpleMovieList(type)
        }

        /**
         * fetch in RemoteDataSource
          */
        val response = remoteDataSource.fecthSimpleMovieList(type)
        if(response.isSuccessful){
            localDataSource.insertSimpleMovieList(response.body()?.result)
            return ResponseResult.Success(response.body(), 1)
        }
        return ResponseResult.Fail(response.body(), 2)
    }

    suspend fun requestMovieDetail(id : Int) : ResponseResult<MovieDetailResponse>{
        /**
         * fetch in LocalDataSource
         */
        if(networkStatus == NetworkStatus.NOT_CONNECTED){
            return localDataSource.fetchMovieDetail(id)
        }

        /**
         * fetch in RemoteDataSource
         */
        val response = remoteDataSource.fetchMovieDetail(id)
        if(response.isSuccessful){
            localDataSource.insertMovieDetail(response.body()?.result?.first())
            return ResponseResult.Success(response.body(),1)
        }
        return ResponseResult.Fail(response.body(), 2)
    }

    suspend fun requestCommentList(id : Int, limit : Int) : ResponseResult<MovieCommentResponse>{
        /**
         * fetch in LocalDataSource
         */
        if(networkStatus == NetworkStatus.NOT_CONNECTED){
            return localDataSource.fetchCommentList(id, limit)
        }

        /**
         * fetch in RemoteDataSource
         */
        val response = remoteDataSource.fecthCommentList(id, limit)
        if(response.isSuccessful){
            localDataSource.insertCommentList(response.body()?.result)
            return ResponseResult.Success(response.body(), 1)
        }
        return ResponseResult.Fail(response.body(),2)
    }
}